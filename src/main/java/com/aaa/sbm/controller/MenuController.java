package com.aaa.sbm.controller;

import com.aaa.sbm.service.MenuService;
import com.aaa.sbm.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ fileName:MenuController
 * @ description:  1，用户登录成功后jwt工具类 生成一个token 返回给前端 前端要把生成token存储到本地对象，
 *                  以后所有请求都需要在请求头中带上该token
 *                2, 后台写一个过滤器filter或者拦截器interceptor  验证是否是登录请求，如果是直接放行，如果不是看请求头中是否含有token
 *                 如果没有，直接提示认证失败，需要登录   如果有，验证token是否正确 如果正确就放行，如果错误提示认证失败，需要登录
 *                3，登录成功后，解析token获取用户信息，获取用户编号，再调用刚才写的queryByUserId 方法，跳转到首页，实现不同用户看到不同菜单
 *                4， jwt工具类 要有生成token和验证解析token的方法
 *
 * @ author:zhz
 * @ createTime:2022/4/11 10:38
 * @ version:1.0.0
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController{


    @Resource
    private MenuService menuService;

    /**
     * 根据编号查询该用户拥有的所有权限
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result queryByUserId(int userId){
        return  success(menuService.queryByUserId(userId));
    }
    /**
     * 查询树形数据
     * @return
     */
    @GetMapping("queryTreeData")
    public Result   queryTreeData(){
        return success(menuService.queryTreeData());
    }
}
