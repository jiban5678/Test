package com.aaa.sbm.shiro;

import com.aaa.sbm.bo.TreeNode;
import com.aaa.sbm.entity.Role;
import com.aaa.sbm.entity.User;
import com.aaa.sbm.service.MenuService;
import com.aaa.sbm.service.RoleService;
import com.aaa.sbm.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ fileName:MyRealm
 * @ description:
 * @ author:zhz
 * @ createTime:2022/5/31 9:59
 * @ version:1.0.0
 */
//@Component
    @Log4j2
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    /**
     * 获取授权数据
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取的是封装的认证信息类SimpleAuthenticationInfo的第一个参数
        User user = (User)principalCollection.getPrimaryPrincipal();
        Integer userId = user.getUserId();
        //封装角色和权限的
        SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
        //根据用户编号查询角色集合
        List<Role> roleList = roleService.queryByUserId(userId);
        log.info("用户"+user.getLoginName()+"具有的角色如下：");
        //直接循环
        for (Role role : roleList) {
            log.info("----------------------"+role.getRoleKey());
            //循环放入角色key    如果登录用户是admin   放入了3个
            simpleAuthorizationInfo.addRole(role.getRoleKey());
        }
       // 根据用户编号查询权限集合
        List<TreeNode> menuList = menuService.queryByUserId(userId);
        log.info("用户"+user.getLoginName()+"具有的权限如下：");
        //直接循环
        for (TreeNode treeNode : menuList) {
            String perms = treeNode.getPerms();
            if(StringUtils.hasText(perms)) {
                log.info("----------------------" + perms);
                //循环放入角色key    如果登录用户是admin   放入了3个
                simpleAuthorizationInfo.addStringPermission(perms);
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 获取认证数据
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //获取用户名  登录方法中收集到登录页面填写的用户名
       String userName =  (String)authenticationToken.getPrincipal();
       //根据用户名或者用户对象
        User user = userService.queryByUsername(userName);
        //判断user是否为空
         if(user==null){
             throw new AccountException();
         }
         //获取加密密码
        String password = user.getPassword();
        //获取盐值
        String salt = user.getSalt();
        return new SimpleAuthenticationInfo(user,//放入用户对象，方便登录成功后，在其他地方获取
                password,//加密密码15da080f1a2c882e2b47a5f2183e01289fbcb9a3038bb149535e037b6efb43e514869a99060522ac148e97aced92fc6d37c99b914fffa29b76dcb9dd9a91d3ad
                ByteSource.Util.bytes(salt.getBytes()),//封装盐值的ByteSource   efdd1d36-2430-4
                getName());//当前realm的名称
    }
}
