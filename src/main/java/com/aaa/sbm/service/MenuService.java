package com.aaa.sbm.service;

import com.aaa.sbm.bo.TreeNode;

import java.util.List;

/**
 * @ fileName:MenuService
 * @ description:
 * @ author:zhz
 * @ createTime:2022/4/11 9:47
 * @ version:1.0.0
 */
public interface MenuService {

    /**
     * 查询菜单数据 ,拼装成树形数据
     * @return
     */
    List<TreeNode> queryTreeData();

    /**
     * 根据用户编号查询该有用于拥有的所有权限
     * @param userId
     * @return
     */
    List<TreeNode> queryByUserId(int userId);
}
