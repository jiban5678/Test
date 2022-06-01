package com.aaa.sbm.vo;

import lombok.Data;

/**
 * @ fileName:QueryPage
 * @ description:同一分页查询实体    作业： entity   vo   bo   po   dto   都是什么意思，在项目如何使用
 * @ author:zhz
 * @ createTime:2022/4/8 13:19
 * @ version:1.0.0
 */
@Data
public class QueryPage<A> {

    private int pageNo;
    private int pageSize;
    private A data;
}
