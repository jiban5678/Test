package com.aaa.sbm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ fileName:GoodsType
 * @ description:商品类型
 * @ author:zhz
 * @ createTime:2022/3/31 10:31
 * @ version:1.0.0
 */
@Data
//@AllArgsConstructor
public class GoodsType implements Serializable {
    public GoodsType() {
        System.out.println("GoodsType空构造方法被调用。。。。。。。。。。。。。。");
    }

    public GoodsType(int typeId, String typeName) {
        System.out.println("GoodsType带参构造方法被调用。。。。。。。。。。。。。。");
        this.typeId = typeId;
        this.typeName = typeName;
    }

    private  int typeId;
    private String typeName;
}
