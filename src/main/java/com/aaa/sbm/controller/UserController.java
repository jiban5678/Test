package com.aaa.sbm.controller;

import com.aaa.sbm.entity.User;
import com.aaa.sbm.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.aaa.sbm.util.Result;
import com.aaa.sbm.vo.QueryPage;


/**
 * 用户信息表(User)表控制层
 *
 * @author makejava
 * @since 2022-05-31 09:24:10
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    /**
     * 用户登录
     * @param user
     * @return
     */
    @GetMapping("login")
    public Result login(User user){
         return this.userService.login(user);
    }
    /**
     * 分页查询
     *
     * @param queryPage 分页查询封装
     * @return 查询结果
     */
    @PostMapping("queryByPage")
    public Result queryByPage(@RequestBody QueryPage<User> queryPage) {
        return success(this.userService.queryByPage(queryPage));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("queryById")
    public Result queryById(Integer id) {
        return success(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @GetMapping("add")
    public Result add(User user) {
        return success(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public Result edit(User user) {
        return success(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("deleteById")
    public Result deleteById(Integer id) {
        return success(this.userService.deleteById(id));
    }

}

