package com.aaa.sbm.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ fileName:InitRunnerA
 * @ description:
 * @ author:zhz
 * @ createTime:2022/4/1 9:47
 * @ version:1.0.0
 */
@Component //交给IOC容器管理
@Order(value = 11)  //如果springboot启动时，要加载多个启动类，确定顺序
public class InitRunnerB implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("InitRunnerBBBBB-------springboot启动时，想做什么事。。。。。通过这完成.....");
    }
}
