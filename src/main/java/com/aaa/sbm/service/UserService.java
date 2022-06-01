package com.aaa.sbm.service;

import com.aaa.sbm.entity.User;
import com.aaa.sbm.util.Result;
import com.aaa.sbm.vo.QueryPage;
import com.github.pagehelper.PageInfo;

/**
 * 用户信息表(User)表服务接口
 *
 * @author makejava
 * @since 2022-05-31 09:24:11
 */
public interface UserService {

    /**
     * 用户登录，因为使用的shiro 登录代码比较长，所有到服务层
     * @param user
     * @return
     */
    Result login(User user);
    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    User queryByUsername(String userName);
    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);

    /**
     * 分页查询
     *
     * @param queryPage 筛选条件
     * @return 查询结果
     */
    PageInfo queryByPage(QueryPage<User> queryPage);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

}
