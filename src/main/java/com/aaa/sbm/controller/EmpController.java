package com.aaa.sbm.controller;

import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ fileName:EmpController
 * @ description:
 * @ author:zhz
 * @ createTime:2022/4/1 11:15
 * @ version:1.0.0
 */
@RestController
@RequestMapping("emp")
public class EmpController extends BaseController{

    @GetMapping("queryByParam")
    public Result queryByParam() {
        //不推荐这样写  代码重复量大

        //模拟异常
        int[] intArray = {1,2};
        System.out.println(intArray[2]);
        /*try {
            return success("模拟人员查询");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error("模拟人员查询");*/
        return success("模拟人员查询");
    }
}
