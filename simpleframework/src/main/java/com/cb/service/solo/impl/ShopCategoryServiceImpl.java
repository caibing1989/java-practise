package com.cb.service.solo.impl;

import com.cb.entity.bo.ShopCategory;
import com.cb.entity.dto.Result;
import com.cb.service.solo.ShopCategoryService;

import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
