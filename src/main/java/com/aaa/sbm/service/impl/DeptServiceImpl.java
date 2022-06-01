package com.aaa.sbm.service.impl;

import com.aaa.sbm.dao.DeptDao;
import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.service.DeptService;
import com.aaa.sbm.util.CustomException;
import com.aaa.sbm.util.ResultStatus;
import com.aaa.sbm.vo.QueryPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ fileName:DeptServiceImpl
 * @ description:
 * @ author:zhz
 * @ createTime:2022/3/31 9:42
 * @ version:1.0.0
 */
@Service
public class DeptServiceImpl implements DeptService {

    //依赖注入dao层
    @Resource
    private DeptDao deptDao;

    @Override
    public PageInfo queryByParam(QueryPage<Dept> queryPage) {
        //分页操作  分页参数一定要进行传递
        if(queryPage==null||queryPage.getPageNo()==0||queryPage.getPageSize()==0){
            //进行业务编写时，可以抛出自定义异常
           throw new CustomException(ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnCode(),
                   ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnMessage());
        }
        //写了大量业务
        /*String str = null;
        System.out.println(str.length());*/ // NullPointException   空指针异常
        PageHelper.startPage(queryPage.getPageNo(),queryPage.getPageSize());
       //获取查询对象
        Dept dept = queryPage.getData();
        return new PageInfo(deptDao.queryByParam(dept));
    }

    @Override
    public int add(Dept dept) {
        return deptDao.add(dept);
    }
}
