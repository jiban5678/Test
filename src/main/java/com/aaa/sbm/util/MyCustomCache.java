package com.aaa.sbm.util;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ fileName:MyCustomCache
 * @ description: 自定义的缓存类   用于调用第三方缓存（我们在这调用的是redis cluster 缓存）
 * @ author:zhz
 * @ createTime:2022/5/9 9:11
 * @ version:1.0.0
 */
public class MyCustomCache  implements Cache {

    //定义缓存对象的唯一识别   mybatis官网 要求必须 提供一个接受 String 参数作为 id 的构造器
    private  String  id;

    //使用在  springboot 启动时，加载配置文件
    // 实例化出jedisConnectionFactory 对象，来进行数据存入和获取及删除相关操作
    private static JedisConnectionFactory jedisConnectionFactory;

    //实例化读写锁对象      多个请求同时对缓存中某一个操作时的策略   策略是： 读读共享  读写互斥 写读互斥  写写互斥
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //使用一个静态注入方式 给当前jedisConnectionFactory对象来赋值
    public static  void  setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
        MyCustomCache.jedisConnectionFactory =jedisConnectionFactory;
    }

    /**
     * 带参构造  官网要求的
     * @param id
     */
    public MyCustomCache(String id) {
        if(null==id){
            throw new RuntimeException("id不能为空，必须传递！！！");
        }
        this.id = id;
    }

    /**
     * 底层调用时，会通过该方法获取id
     * @return
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 向缓存中添加数据
     * @param key
     * @param val
     */
    @Override
    public void putObject(Object key, Object val) {
        //通过实例化连接redis集群的工厂类，获取一个redis连接   静态属性在非静态方法中可以直接使用  静态属性在类加载时就被放入到所有内存共享区域-方法区  所有对象都是共享
        RedisConnection connection = jedisConnectionFactory.getConnection();
        // 因为存储时存储的是字节数组类型  所以key和value都要经过序列化
        // 使用springboot整合redis提供的序列化方式
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer =
                new JdkSerializationRedisSerializer();
        //使用该类，序列化key和value    a=1   变为:  aserialize =  1serialize
        byte[] serializeKey = jdkSerializationRedisSerializer.serialize(key);
        byte[] serializeVal = jdkSerializationRedisSerializer.serialize(val);
        // 通过RedisConnection可以执行redis所有命令
        //在此使用spring类型操作，向集群中存入数据  存入的key和value都是序列化过的字节数组
        connection.set(serializeKey,serializeVal);
       // 这里面不要手动关闭连接，因为配置的有连接池，连接的使用和关闭由连接池来完成
        // connection.close();
    }

    /**
     * 通过key获取缓存对象
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        //通过实例化连接redis集群的工厂类，获取一个redis连接   静态属性在非静态方法中可以直接使用  静态属性在类加载时就被放入到所有内存共享区域-方法区  所有对象都是共享
        RedisConnection connection = jedisConnectionFactory.getConnection();
// 因为存储时存储的是字节数组类型  所以key和value都要经过序列化
        // 使用springboot整合redis提供的序列化方式
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer =
                new JdkSerializationRedisSerializer();
        //  serializeKey =  a
        //序列key变为字节数组
        byte[] serializeKey = jdkSerializationRedisSerializer.serialize(key);
        //通过序列化的key获取到序列化后的值
        byte[] serializeValue = connection.get(serializeKey);
        //反序列化 后 并返回     1serialize-> 1
        return jdkSerializationRedisSerializer.deserialize(serializeValue);
    }

    /**
     * 根据key删除缓存对象
     * @param key
     * @return
     */
    @Override
    public Object removeObject(Object key) {
        //通过实例化连接redis集群的工厂类，获取一个redis连接   静态属性在非静态方法中可以直接使用  静态属性在类加载时就被放入到所有内存共享区域-方法区  所有对象都是共享
        RedisConnection connection = jedisConnectionFactory.getConnection();
        // 使用springboot整合redis提供的序列化方式
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer =
                new JdkSerializationRedisSerializer();
        //  serializeKey =  a
        //序列key变为字节数组
        byte[] serializeKey = jdkSerializationRedisSerializer.serialize(key);
        //删除时也要使用序列化后的key  这种写法当在大量缓存进行删除时，效率会低    每次都会执行删除
        //connection.del(serializeKey);
        //设置该key失效   0为立马失效   立马失效，就获取不到，等同于删除    底层redis使用惰性删除  定期批量清理失效的key 批量操作，而不是一个一个操纵，提高效率
        return  connection.expire(serializeKey,0);
    }

    /**
     * 清空所有缓存数据
     */
    @Override
    public void clear() {
        //通过实例化连接redis集群的工厂类，获取一个redis连接   静态属性在非静态方法中可以直接使用  静态属性在类加载时就被放入到所有内存共享区域-方法区  所有对象都是共享
        RedisConnection connection = jedisConnectionFactory.getConnection();
        //flushDb  redis每个实例对象默认分为16库   清空当前库
        //connection.flushDb();
        //flushAll  清空所有库
        connection.flushAll();
    }

    /**
     * 获取当前缓存中对象数量
     * @return
     */
    @Override
    public int getSize() {
        //通过实例化连接redis集群的工厂类，获取一个redis连接   静态属性在非静态方法中可以直接使用  静态属性在类加载时就被放入到所有内存共享区域-方法区  所有对象都是共享
        RedisConnection connection = jedisConnectionFactory.getConnection();
        //使用dbSize  int
        Long aLong = connection.dbSize();
        //转为int类型，并返回
        return  Integer.valueOf(aLong.toString());
    }

    /**
     * 缓存读写锁  （缓存读写策略）
     * @return
     */
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
