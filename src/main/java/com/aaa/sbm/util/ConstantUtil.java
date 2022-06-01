package com.aaa.sbm.util;

/**
 * @ fileName:ConstantUtil
 * @ description: 常量类  一旦确定不允许随便更改
 * @ author:zhz
 * @ createTime:2022/5/31 10:04
 * @ version:1.0.0
 */
public class ConstantUtil {
    /**
     * 订单状态常量
     */
    public interface  OrderStatus{

    }
    /**
     * shiro 认证匹配器常量
     */
    public interface  ShiroCM{
         String  ALGORITHM_NAME="SHA-512";
         int ITERATIONS=1024;
    }
}
