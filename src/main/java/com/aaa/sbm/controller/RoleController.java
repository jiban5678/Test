package com.aaa.sbm.controller;

import com.aaa.sbm.entity.Role;
import com.aaa.sbm.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.aaa.sbm.util.Result;
import com.aaa.sbm.vo.QueryPage;


/**
 * 角色信息表(Role)表控制层
 *
 * @author makejava
 * @since 2022-05-31 09:29:42
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 分页查询
     *
     * @param queryPage 分页查询封装
     * @return 查询结果
     */
    @PostMapping("queryByPage")
    public Result queryByPage(@RequestBody QueryPage<Role> queryPage) {
        return success(this.roleService.queryByPage(queryPage));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("queryById")
    public Result queryById(Integer id) {
        return success(this.roleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public Result add(Role role) {
        return success(this.roleService.insert(role));
    }

    /**
     * 编辑数据
     *
     * @param role 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public Result edit(Role role) {
        return success(this.roleService.update(role));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("deleteById")
    public Result deleteById(Integer id) {
        return success(this.roleService.deleteById(id));
    }

}

