package com.kangyonggan.cms.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kangyonggan
 * @date 4/20/18
 */
@Data
public class Page<T> implements Serializable {

    public Page() {

    }

    public Page(List<T> rows) {
        PageInfo<T> pageInfo = new PageInfo<>(rows);
        this.total = pageInfo.getTotal();
        this.rows = rows;
    }

    /**
     * 总记录数
     */
    private long total;

    /**
     * 数据
     */
    private List<T> rows;

}
