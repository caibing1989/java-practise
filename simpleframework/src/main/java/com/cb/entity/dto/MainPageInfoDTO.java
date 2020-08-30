package com.cb.entity.dto;

import com.cb.entity.bo.HeadLine;
import com.cb.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;

    private List<ShopCategory> shopCategoryList;
}
