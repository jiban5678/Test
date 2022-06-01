package com.aaa.sbm.dao;

import com.aaa.sbm.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ fileName:DeptDao
 * @ description:
 * @ author:zhz
 * @ createTime:2022/3/31 9:28
 * @ version:1.0.0
 */
//@Repository
//@Mapper  //加Mapper 注解也可以，启动类上就可以不加
public interface DeptDao {

    /**
     * 部门带参查询方法
     * @param dept
     * @return
     */
    List<Dept>  queryByParam(Dept dept);

    /**
     * 部门添加
     * @param dept
     * @return
     */
    int add(Dept dept);
}
