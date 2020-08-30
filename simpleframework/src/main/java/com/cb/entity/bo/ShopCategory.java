package com.cb.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Data
public class ShopCategory {
    private Long shopCategoryId;

    private String shopCategoryName;

    private String shopCategoryDesc;

    private String shopCategoryImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private ShopCategory parent;
}
