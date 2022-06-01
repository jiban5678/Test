package com.aaa.sbm.util;

/**
 * @ fileName:ResultStatus
 * @ description: 为了统一管理返回值及信息提示，不让每个开发人员，各自写各自，造成前端无法统一判断，使用枚举类，枚举常量
 * @ author:zhz
 * @ createTime:2022/4/2 9:31
 * @ version:1.0.0
 */
public enum ResultStatus {
    //枚举两个类型  默认调用空构造，但是传递了2个参数，使用带参构造
    SUCCESS(2000,"操作成功"),
    ERROR(5000,"操作失败"),
    NOT_ALLOWED_ACCESS(5001,"不允许访问"),
    ARGUMENT_IS_NOT_NULL(5002,"参数不能为空");  //如果还想自定义其他返回异常，可以继续

    //定义两个属性
    private int returnCode;
    private String returnMessage;

    ResultStatus(int returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
