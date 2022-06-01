package com.aaa.sbm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication  //是一个复合注解
@EnableSwagger2 //一定要开启
//@SpringBootApplication(exclude = DataSource.class) // exclude 项目启动时，要忽略加载类在此配置
@MapperScan("com.aaa.sbm.dao")  //扫描dao层
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
         SpringApplication.run(SpringbootMybatisApplication.class, args);
        //ApplicationContext applicationContext = SpringApplication.run(SpringbootMybatisApplication.class, args);

       /*  GoodsType goodsType =    (GoodsType)applicationContext.getBean("goodsType");
       System.out.println(goodsType);
        goodsType.setTypeId(111);
        System.out.println(goodsType.getTypeId());*/
    }

}
