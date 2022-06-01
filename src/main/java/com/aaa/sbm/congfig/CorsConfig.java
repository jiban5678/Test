package com.aaa.sbm.congfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ fileName:CorsConfig
 * @ description: springboot全局跨域配置
 * @ author:zhz
 * @ createTime:2022/4/8 9:47
 * @ version:1.0.0
 */
//@Configuration  //cors-config.xml   <beans>
public class CorsConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //链式编程
        registry.addMapping("/**") //允许那些url请求跨域  /**代表所有请求
        //.allowedOrigins("http://localhost:8088/","http://localhost:8080/","https://192.168.31.174:7777/")  //允许该域跨域  可以配置多个
        //.allowedOrigins("*") //* 统配所有域
        .allowedOriginPatterns("*") //允许域的请求的路径    *统配所有域
        //.allowedMethods("GET","POST","PUT","DELETE","OPTIONS") //允许请求方法类型
        .allowedMethods("*") // * 代表所有请求方法类型都可以
        //.allowedHeaders("header1", "header2", "header3","access-token") //跨域时，允许header头部携带哪些参数  例如header1 ....
        .allowedHeaders("*") // 跨域时，允许header头部携带任何参数
        .allowCredentials(true) //跨域请求时，是否允许携带并处理cookie(前后端不分离时，使用cookie存储sessionid) ,如果写成true上面allowedOrigins不可以使用*
        .maxAge(3600);//预检请求检测间隔时长   在对某一个域  http://localhost:8088/ 进行一次预检成功后，该预检会被缓存，接下来的1小时，都不再发options请求
         //几乎所有的ajax异步请求，除了get  几乎其他所有的方式都会先发一个options请求，测试是否可以正常访问资源（跨域） 如果请求成功，再发真实请求
    }
}
