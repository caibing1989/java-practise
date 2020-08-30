package com.cb.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Data
public class HeadLine {
    private Long lineId;

    private String lineName;

    private String lineLink;

    private String lineImg;

    private Integer priority;

    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;
}
