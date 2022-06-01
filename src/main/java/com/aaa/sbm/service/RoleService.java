package com.aaa.sbm.service;

import com.aaa.sbm.entity.Role;
import com.aaa.sbm.vo.QueryPage;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2022-05-31 09:29:42
 */
public interface RoleService {

    /**
     * 根据用户编号查询该用户有对应的所有角色
     *
     * @param userId 主键
     * @return 实例对象
     */
    List<Role> queryByUserId(Integer userId);
    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    Role queryById(Integer roleId);

    /**
     * 分页查询
     *
     * @param queryPage 筛选条件
     * @return 查询结果
     */
    PageInfo queryByPage(QueryPage<Role> queryPage);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);

}
