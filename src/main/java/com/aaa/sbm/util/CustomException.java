package com.aaa.sbm.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ fileName:CustomException
 * @ description:自定义异常
 * @ author:zhz
 * @ createTime:2022/4/2 10:29
 * @ version:1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

}
