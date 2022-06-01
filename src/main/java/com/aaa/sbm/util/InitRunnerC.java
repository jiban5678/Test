package com.aaa.sbm.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ fileName:InitRunnerC
 * @ description:
 * @ author:zhz
 * @ createTime:2022/4/1 9:50
 * @ version:1.0.0
 */
@Component
@Order(value = 12)
public class InitRunnerC implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("InitRunnerCCCCCC-------springboot启动时，想做什么事。。。。。通过这完成.....");
    }
}
