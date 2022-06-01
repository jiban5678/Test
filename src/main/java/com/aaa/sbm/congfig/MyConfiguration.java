package com.aaa.sbm.congfig;

import com.aaa.sbm.entity.Goods;
import com.aaa.sbm.entity.GoodsType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ fileName:MyConfiguration
 * @ description:
 * @ author:zhz
 * @ createTime:2022/3/31 10:33
 * @ version:1.0.0
 */
@Configuration  //相当于过去的xml中的Beans 这里面会包含一个一个的<bean>
public class MyConfiguration {

    /**
     * 借助filter实现跨域   实现类CorsFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);//跨域请求时，是否允许携带并处理cookie 如果写成true上面allowedOrigins不可以使用*
        //config.addAllowedOrigin("http://localhost:8088/"); //* 统配所有域
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");// 跨域时，* 允许header头部携带任何参数
        config.addAllowedMethod("*");// * 代表所有请求方法类型都可以
        source.registerCorsConfiguration("/**", config);//允许那些url请求跨域  /**代表所有请求
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
    /**
     * 实例化出一个bean 当run启动运行时 都会把@Configuration读取里面的bean  并交给ioc容器管理
     * @return
     */
    //@Bean("gt")  //相当于bean  <bean id='gt' class='com.aaa.sbm.entity.GoodsType'>  id=方法的名称    class=该方法的返回值
    @Bean  //相当于bean  <bean id='goodsType' class='com.aaa.sbm.entity.GoodsType'>  id=方法的名称    class=该方法的返回值
    public GoodsType goodsType(){
        return new GoodsType(11,"上衣");
    }


    /**
     * 实例化商品
     * @return
     */
    @Bean  //<bean id=goods class='com.aaa.sbm.entity.Goods'><constructor-arg index=0 value=1><constructor-arg index=1 value=休闲夏装> <constructor-arg index=2 ref='goodsType'></bean>
    public Goods goods(){
        return new Goods(1,"休闲夏装",goodsType());
    }


}
