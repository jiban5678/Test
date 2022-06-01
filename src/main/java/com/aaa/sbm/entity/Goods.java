package com.aaa.sbm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ fileName:Goods
 * @ description:商品   模拟过去配置xml方式，商品和商品分类交给ioc容器管理
 * @ author:zhz
 * @ createTime:2022/3/31 10:31
 * @ version:1.0.0
 */
@Data
//@AllArgsConstructor
public class Goods implements Serializable {

    public Goods() {
        System.out.println("Goods空构造方法被调用。。。。。。。。。。。。。。");
    }

    public Goods(int id, String goodsName, GoodsType goodsType) {
        System.out.println("Goods带参构造方法被调用。。。。。。。。。。。。。。");
        this.id = id;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
    }

    private int id;
    private String goodsName;
    private GoodsType goodsType;
}
