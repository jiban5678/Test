package com.aaa.sbm.service.impl;

import com.aaa.sbm.entity.User;
import com.aaa.sbm.dao.UserDao;
import com.aaa.sbm.service.UserService;
import com.aaa.sbm.util.ConstantUtil;
import com.aaa.sbm.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.aaa.sbm.util.CustomException;
import com.aaa.sbm.util.ResultStatus;
import com.aaa.sbm.vo.QueryPage;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;
import java.util.UUID;

/**
 * 用户信息表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-05-31 09:24:11
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public Result login(User user) {
        //用户名密码任意为空，直接报错
        if(!StringUtils.hasText(user.getUserName())||!StringUtils.hasText(user.getPassword())){
            throw  new CustomException(ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnCode(),
                    ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnMessage());
        }
        //收集用户信息
        AuthenticationToken authenticationToken = 
                new UsernamePasswordToken(user.getUserName(), user.getPassword());

        //获取用户对象 主题
        Subject currentUser = SecurityUtils.getSubject();

        //定义错误接受变量
        String  errorInfo = "";
        try {
            //进行登录
            currentUser.login(authenticationToken);
            //获取安全session
            Session session = currentUser.getSession();
            //获取用户信息 获取MyRealm封装认证信息SimpleAuthenticationInfo的第一个参数
            User  resultUser  = (User)currentUser.getPrincipal();
            //放入session
            session.setAttribute("userInfo",resultUser);
            return new Result(ResultStatus.SUCCESS.getReturnCode(),
                    ResultStatus.SUCCESS.getReturnMessage(),"登录成功！");
        }catch (AccountException e){
            e.printStackTrace();
            errorInfo="用户名错误！";
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            errorInfo="密码错误！";
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
            errorInfo="用户名或密码错误！";
        }
        return new Result(ResultStatus.ERROR.getReturnCode(),
                ResultStatus.ERROR.getReturnMessage(),errorInfo);
    }

    @Override
    public User queryByUsername(String userName) {
        return userDao.queryByUsername(userName);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userId) {
        return this.userDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param queryPage 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(QueryPage<User> queryPage) {
        //分页操作  分页参数一定要进行传递
        if (queryPage == null || queryPage.getPageNo() == 0 || queryPage.getPageSize() == 0) {
            //进行业务编写时，可以抛出自定义异常
            throw new CustomException(ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnCode(),
                    ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnMessage());
        }
        PageHelper.startPage(queryPage.getPageNo(), queryPage.getPageSize());
        //获取查询对象
        User user = queryPage.getData();
        return new PageInfo(userDao.queryAllByLimit(user));
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        //获取用户填写的密码oldPassword ="123456"
        String oldPassword = user.getPassword();
        //随机盐值
        String saltValue = UUID.randomUUID().toString();
        //计算加密密码
        Sha512Hash sha512Hash = new Sha512Hash(oldPassword,saltValue, ConstantUtil.ShiroCM.ITERATIONS);
        //设置加密密码
        user.setPassword(sha512Hash.toString());
        //设置盐值
        user.setSalt(saltValue);
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.userDao.deleteById(userId) > 0;
    }
}
