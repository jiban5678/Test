package com.aaa.sbm.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ fileName:Result
 * @ description: 定义全局返回结果类
 * @ author:zhz
 * @ createTime:2022/4/2 9:24
 * @ version:1.0.0
 */
//类泛型  编写代码时，使用任意字符做占位符，真正实例化对象时，传入相应真正类型
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;
}
