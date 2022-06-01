package com.aaa.sbm.congfig;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ fileName:SwaggerConfiguration
 * @ description:
 * @ author:zhz
 * @ createTime:2022/4/1 11:07
 * @ version:1.0.0
 */
@Configuration // swagger-configuration.xml  <beans>...
public class SwaggerConfiguration {
    /**
     * 创建一个docket
     * @return
     */
    @Bean
    public Docket docket() {
        //链式编程    好多API都有体现
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //依赖注入apiInfo()
                //.enable(false)  //swagger不能访问
                .select()
                //配置要扫描接口的方式
                 // 配置路径扫描
                .apis(RequestHandlerSelectors.basePackage("com.aaa.sbm.controller"))
                 //识别注解方式   RequestHandlerSelectors请求处理选择器，根据类上的注解识别  类上含有ApiOperation注解，就交给swagger管理
                // .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                //路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 在页面上呈现一些信息  apiInfo
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多请关注http://www.baidu.com")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact(new Contact("时军涛","https://www.baidu.com","521@qq.com"))
                .version("1.0")
                .build();
    }
}
