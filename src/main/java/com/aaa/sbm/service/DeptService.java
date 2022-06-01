package com.aaa.sbm.service;

import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.vo.QueryPage;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ fileName:DeptService
 * @ description:
 * @ author:zhz
 * @ createTime:2022/3/31 9:41
 * @ version:1.0.0
 */
public interface DeptService {

    /**
     * 部门带参查询方法
     * @param queryPage
     * @return
     */
    PageInfo queryByParam(QueryPage<Dept> queryPage);

    /**
     * 部门添加
     * @param dept
     * @return
     */
    int  add(Dept dept);
}
