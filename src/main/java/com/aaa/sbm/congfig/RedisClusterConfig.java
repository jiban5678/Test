package com.aaa.sbm.congfig;

import com.aaa.sbm.property.RedisProperty;
import com.aaa.sbm.util.MyCustomCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @ fileName:RedisClusterConfig
 * @ description:
 * @ author:zhz
 * @ createTime:2022/5/7 10:21
 * @ version:1.0.0
 */
@Configuration  //相当于 redis-cluster-config.xml <beans></beans>
public class RedisClusterConfig {

    //依赖注入
    @Resource
    private RedisProperty redisProperty;


    /**
     * 配置JedisConnectionFactory redis连接工厂
     * @return
     */
    @Bean //<bean id=jedisConnectionFactory class =org.springframework.data.redis.connection.jedis.JedisConnectionFactory>
    public JedisConnectionFactory  jedisConnectionFactory(){
        //实例化对象  使用 redis集群 + redis连接池
        //   RedisClusterConfiguration clusterConfig, JedisPoolConfig poolConfig
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory(clusterConfig(),poolConfig());
        //返回对象
        return jedisConnectionFactory;
    }

    /**
     * redis集群配置
     * @return
     */
    @Bean
    public RedisClusterConfiguration clusterConfig(){
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
       //这种写法完全可以，但是硬编码，不利于redis数据源更换
        /* RedisNode redisNode1 = new RedisNode("192.168.170.31",8001);
        redisClusterConfiguration.addClusterNode(redisNode1);
        RedisNode redisNode2 = new RedisNode("192.168.170.32",8002);
        redisClusterConfiguration.addClusterNode(redisNode2);
        RedisNode redisNode3 = new RedisNode("192.168.170.32",8003);
        redisClusterConfiguration.addClusterNode(redisNode3);
        RedisNode redisNode4 = new RedisNode("192.168.170.32",8004);
        redisClusterConfiguration.addClusterNode(redisNode4);
        RedisNode redisNode5 = new RedisNode("192.168.170.33",8005);
        redisClusterConfiguration.addClusterNode(redisNode5);
        RedisNode redisNode6 = new RedisNode("192.168.170.33",8006);*/
        //nodes=192.168.170.31:8001,192.168.170.31:8002,192.168.170.32:8003,192.168.170.32:8004,192.168.170.33:8005,192.168.170.33:8006
        String nodes = redisProperty.getNodes();
        // nodeArray = ["192.168.170.31:8001","192.168.170.31:8002",192.168.170.32:8003,192.168.170.32:8004,192.168.170.33:8005,192.168.170.33:8006]
        String[] nodeArray = nodes.split(",");
        //第1次ipPort = 192.168.170.31:8001  第2次 ipPort = 192.168.170.31:8002 ....
        for (String  ipPort : nodeArray) {
            //第1次ipPortArray = ["192.168.170.31","8001"]  第2次 ipPortArray = ["192.168.170.31","8002"] ....
            String[] ipPortArray = ipPort.split(":");
            //实例化节点
            RedisNode redisNode = new RedisNode(ipPortArray[0],Integer.valueOf(ipPortArray[1]));
            //添加节点到集群配置类中
            redisClusterConfiguration.addClusterNode(redisNode);
        }

        return  redisClusterConfiguration;
    }

    /***
     * redis连接池配置
     * @return
     */
    @Bean
    public JedisPoolConfig poolConfig(){
        //实例化连接池配置  企业实战中可以更多自定义配置
        JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperty.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisProperty.getMaxTotal());
        jedisPoolConfig.setTestOnBorrow(redisProperty.isTestOnBorrow());
        jedisPoolConfig.setMaxWaitMillis(redisProperty.getMaxWaitMillis());
        return jedisPoolConfig;
    }

    /**
     * 特殊的bean配置  在springboot启动时也会被执行
     * 没有返回值，目的是调用静态注入方法，把当前配置完成的jedisConnectionFactory
     * 赋值给MyCustomCache中的属性
     */
    @Bean
    public void  setJCF(){
        //调用MyCustomCache 静态方法，把jedisConnectionFactory注入进去
        MyCustomCache.setJedisConnectionFactory(jedisConnectionFactory());
    }
}
