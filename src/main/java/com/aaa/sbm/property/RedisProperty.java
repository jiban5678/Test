package com.aaa.sbm.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ fileName:RedisProperty
 * @ description:redis配置属性类（springboot加载自定义配置文件）
 * @ author:zhz
 * @ createTime:2022/5/7 10:07
 * @ version:1.0.0
 */
@Data
@Component //不在3层之内就使用组件注解 交给ioc容器管理
@PropertySource("classpath:project.properties") //spring 指定本类要加载的配置文件路径
@ConfigurationProperties(prefix = "redis") //加载到配置文件后，读取配置文件中以redis开头的配置 和本类中属性一致
public class RedisProperty {

    //最大能够保持空闲状态的链接数
    private int  maxIdle;
            //最大连接数
    private int  maxTotal;
            //最大的等待时长 毫秒
    private int  maxWaitMillis;
            //当调用borrow Object方法时，是否进行有效性检查
    private boolean  testOnBorrow;
            //集群节点配置
    private String  nodes;
}
