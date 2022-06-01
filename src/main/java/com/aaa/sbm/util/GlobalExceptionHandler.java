package com.aaa.sbm.util;

import com.aaa.sbm.controller.BaseController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ fileName:GlobalExceptionHandler
 * @ description: 统一异常处理工具类
 * @ author:zhz
 * @ createTime:2022/4/2 10:02
 * @ version:1.0.0
 */
@RestControllerAdvice  //所有的controller都会走该注解修饰的类，一般用于统一异常获取其他业务处理
public class GlobalExceptionHandler  {

    /**
     * 统一异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result  handlerException(Exception e){
        //异常堆栈仍然打印，要不然找不到报错行
        e.printStackTrace();
        //处理自定义异常
          //判断当前异常是否是自定义异常   instanceof  专门判断一个对象是否属于某一个类
       if(e instanceof CustomException){
          CustomException customException =  (CustomException)e;
          return new Result(customException.getErrorCode(),customException.getErrorMessage(),
                  "业务自定义异常");
       }

      return new Result(ResultStatus.ERROR.getReturnCode(),ResultStatus.ERROR.getReturnMessage(),
              e.getClass().getName());
    }
}
