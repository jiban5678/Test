package com.aaa.sbm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ fileName:Dept
 * @ description: 部门实体类  一旦开启二级缓存，所有的实体类，就必须要序列化，如果不写，会报错
 * @ author:zhz
 * @ createTime:2022/3/31 9:28
 * @ version:1.0.0
 */
@Data
public class Dept implements Serializable {

    //分页的相关参数，实际使用的时候，可能不这样使用
    /*private int pageNo;
    private int pageSize;*/

    private int deptNo;
    private String deptName;
    private String loc;
}
