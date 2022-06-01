package com.aaa.sbm.service.impl;

import com.aaa.sbm.bo.TreeNode;
import com.aaa.sbm.dao.MenuDao;
import com.aaa.sbm.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ fileName:MenuServiceImpl
 * @ description:
 * @ author:zhz
 * @ createTime:2022/4/11 9:49
 * @ version:1.0.0
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    //IOC又叫DI   
    @Resource  
    private MenuDao menuDao;


    @Override
    public List<TreeNode> queryByUserId(int userId) {
        List<TreeNode> treeNodeList = menuDao.queryByUserId(userId);
        //定义返回list  用于拼装数据后，返回
        List<TreeNode> returnTreeList = new ArrayList<>();
        //循环遍历,拼装树形数据
        for (TreeNode treeNode : treeNodeList) {
            //判断是否是一级菜单
            if(treeNode.getParentId()==0){
                //如果是一级菜单 就直接假加入到集合中
                returnTreeList.add(treeNode);
                //查找当前一级节点是否有子节点，如果有，加入孩子节点集合中
                bindChildren(treeNode,treeNodeList);
            }
        }
        return  returnTreeList;
    }

    @Override
    public List<TreeNode> queryTreeData() {
        //获取所有的菜单数据
        List<TreeNode> treeNodeList = menuDao.queryAll();
        //定义返回list  用于拼装数据后，返回
        List<TreeNode> returnTreeList = new ArrayList<>();
        //循环遍历,拼装树形数据
        for (TreeNode treeNode : treeNodeList) {
            //判断是否是一级菜单
            if(treeNode.getParentId()==0){
                //如果是一级菜单 就直接假加入到集合中
                returnTreeList.add(treeNode);
                //查找当前一级节点是否有子节点，如果有，加入孩子节点集合中
                bindChildren(treeNode,treeNodeList);
            }
        }

        return  returnTreeList;
    }

    /**
     * 查找并绑定子节点
     * @param currentTreeNode
     * @param treeNodeList
     */
    private  void   bindChildren(TreeNode currentTreeNode, List<TreeNode> treeNodeList){
        //第1次节点先传入是系统管理  查找系统管理的子节点   第2次客户管理   第3次文档查看 ...
        for (TreeNode treeNode : treeNodeList) {
            //判断当前循环节点的父节点是否和当前节点的ID相等 如果相等，说明当前循环节点是当前节点的孩子
            if(treeNode.getParentId()==currentTreeNode.getId()){
                //获取当前节点的子节点集合
                List<TreeNode> children = currentTreeNode.getChildren();
                // 要加入第一个子节点时，当前集合一定是空的  直接添加孩子，肯定会报NullPointException
                if(children==null){
                    //一定要实例化对象
                      children = new ArrayList<>();
                }
                //把当前循环节点添加到当前节点孩子集合中
                children.add(treeNode);
                //再次让当前节点和孩子集合进行绑定
                currentTreeNode.setChildren(children);
                //自己调用自己，查询当前节点是否还孩子节点  递归
                bindChildren(treeNode,treeNodeList);
            }
        }
    }
}
