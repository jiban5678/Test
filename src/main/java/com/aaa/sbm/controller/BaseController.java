package com.aaa.sbm.controller;

import com.aaa.sbm.util.Result;
import com.aaa.sbm.util.ResultStatus;

/**
 * @ fileName:BaseController
 * @ description:定义所有controller的一个父类，写一些所有controller都可以使用的公共方法
 * @ author:zhz
 * @ createTime:2022/4/2 9:41
 * @ version:1.0.0
 */
public class BaseController {

    /**
     * 封装统一正确返回方法
     * @param data
     * @param <T>
     * @return
     */
    protected <T>  Result success(T data){
      return new Result(ResultStatus.SUCCESS.getReturnCode(),
              ResultStatus.SUCCESS.getReturnMessage(),data);
    }

    /**
     * 封装统一错误返回方法
     * @param data
     * @param <T>
     * @return
     */
    protected <T>  Result error(T data){
        return new Result(ResultStatus.ERROR.getReturnCode(),
                ResultStatus.ERROR.getReturnMessage(),data);
    }
}
