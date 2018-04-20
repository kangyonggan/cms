package com.kangyonggan.cms.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangyonggan
 * @date 4/20/18
 */
@Data
public class Params implements Serializable {

    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 升序/降序
     */
    private String order;

    /**
     * 查询条件
     */
    private Query query;

}
