package com.cb.service.combine.impl;

import com.cb.entity.bo.HeadLine;
import com.cb.entity.bo.ShopCategory;
import com.cb.entity.dto.MainPageInfoDTO;
import com.cb.entity.dto.Result;
import com.cb.service.combine.HeadLineShopCategoryCombineService;
import com.cb.service.solo.HeadLineService;
import com.cb.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    private HeadLineService headLineService;
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        // 获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);

        // 获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);

        // 合并两者并返回
        Result<MainPageInfoDTO> mainPageInfoDTOResult = mergeMainPageInfoResult(headLineResult, shopCategoryResult);
        return mainPageInfoDTOResult;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }


}
