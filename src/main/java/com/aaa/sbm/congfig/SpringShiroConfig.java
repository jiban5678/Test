package com.aaa.sbm.congfig;

import com.aaa.sbm.shiro.MyRealm;
import com.aaa.sbm.util.ConstantUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ fileName:SpringShiroConfig
 * @ description:
 * @ author:zhz
 * @ createTime:2022/5/31 9:44
 * @ version:1.0.0
 */
@Configuration //相当于spring-shiro-config.xml  <beans></beans>
public class SpringShiroConfig {

    /**
     * 实例化ShiroFilterFactoryBean  拦截到所有的请求，根据请求的不同做不同的处理
     *                         配置处理请求的各种方式，配置SecurityManager 等等
     * @return
     */
    @Bean //<bean id=shiroFilter class = org.apache.shiro.spring.web.ShiroFilterFactoryBean>
    public ShiroFilterFactoryBean  shiroFilter(){
        //实例化ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //依赖注入
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //未认证默认跳转地址
        shiroFilterFactoryBean.setLoginUrl("/html/login.html");
        //登录成功后，默认跳转地址
        //shiroFilterFactoryBean.setSuccessUrl("/html/index.html");
        //访问未授权的url跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/html/unauthorized.html");
        //配置不同访问路径的权限
          //定义一个map集合  LinkedHashMap  按照放入顺序进行获取
          Map chainDefinitionMap  = new LinkedHashMap();
          //设置哪些路径未认证时允许访问
          //底层调用 AnonymousFilter 会对key中配置的路径直接放行
          chainDefinitionMap.put("/html/login.html","anon");
          chainDefinitionMap.put("/user/login","anon");
          chainDefinitionMap.put("/user/add","anon");
          chainDefinitionMap.put("/js/**","anon");
          chainDefinitionMap.put("/css/**","anon");
          chainDefinitionMap.put("/imgs/**","anon");
          chainDefinitionMap.put("/images/**","anon");
          //用户退出，注销 LogoutFilter
          chainDefinitionMap.put("/logout111","logout");
          //配置拦截的路径 FormAuthenticationFilter
          chainDefinitionMap.put("/**","authc");
         /*
           # some example chain definitions:
            /admin/** = authc, roles[admin]   使用url访问时 url含有 /admin 路径 需要认证 并且要具备admin角色
           /docs/** = authc, perms[document:read]    使用url访问时 url含有 /docs 路径 需要认证 并且要具备document:read 权限
          = authc
           */
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 实例化shiro核心概念类 DefaultWebSecurityManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //依赖注入Realm
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    /**
     * 配置realm 获取安全数据类
     * @return
     */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm =new MyRealm();
        //设置认证校验
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }

    /**
     * 加密算法类
     * @return
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher =new  HashedCredentialsMatcher();
       //设置算法名称
        hashedCredentialsMatcher.setHashAlgorithmName(ConstantUtil.ShiroCM.ALGORITHM_NAME);
        //设置hash次数
        hashedCredentialsMatcher.setHashIterations(ConstantUtil.ShiroCM.ITERATIONS);
        return hashedCredentialsMatcher;
    }



}
