package com.aaa.sbm.dao;

import com.aaa.sbm.bo.TreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ fileName:MenuDao
 * @ description:
 * @ author:zhz
 * @ createTime:2022/4/11 9:33
 * @ version:1.0.0
 */
//@Mapper
public interface MenuDao {

    /**
     * 查询所有
     * @return
     */
     List<TreeNode>  queryAll();

    /**
     * 根据用户编号查询该有用于拥有的所有权限
     * @param userId
     * @return
     */
     List<TreeNode> queryByUserId(int userId);
}
