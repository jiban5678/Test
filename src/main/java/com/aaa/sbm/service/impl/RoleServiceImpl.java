package com.aaa.sbm.service.impl;

import com.aaa.sbm.entity.Role;
import com.aaa.sbm.dao.RoleDao;
import com.aaa.sbm.service.RoleService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.aaa.sbm.util.CustomException;
import com.aaa.sbm.util.ResultStatus;
import com.aaa.sbm.vo.QueryPage;


import javax.annotation.Resource;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-05-31 09:29:42
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> queryByUserId(Integer userId) {
        return roleDao.queryByUserId(userId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer roleId) {
        return this.roleDao.queryById(roleId);
    }

    /**
     * 分页查询
     *
     * @param queryPage 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(QueryPage<Role> queryPage) {
        //分页操作  分页参数一定要进行传递
        if (queryPage == null || queryPage.getPageNo() == 0 || queryPage.getPageSize() == 0) {
            //进行业务编写时，可以抛出自定义异常
            throw new CustomException(ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnCode(),
                    ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnMessage());
        }
        PageHelper.startPage(queryPage.getPageNo(), queryPage.getPageSize());
        //获取查询对象
        Role role = queryPage.getData();
        return new PageInfo(roleDao.queryAllByLimit(role));
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.roleDao.deleteById(roleId) > 0;
    }
}
