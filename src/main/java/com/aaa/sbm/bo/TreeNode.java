package com.aaa.sbm.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @ fileName:TreeNode
 * @ description: 树形节点实体   通用的，项目中任何想写成树形结构的表，都可以使用该实体
 * @ author:zhz
 * @ createTime:2022/4/11 9:26
 * @ version:1.0.0
 */
@Data
public class TreeNode implements Serializable {

    private  int  id;
    private String label;
    private int parentId;
    private String url;
    private String perms;
    private String icon;
    private List<TreeNode> children;


}
