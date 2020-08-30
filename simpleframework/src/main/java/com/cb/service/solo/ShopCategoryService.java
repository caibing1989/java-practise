package com.cb.service.solo;

import com.cb.entity.bo.ShopCategory;
import com.cb.entity.dto.Result;

import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public interface ShopCategoryService {
    Result<Boolean> addShopCategory(ShopCategory shopCategory);

    Result<Boolean> removeShopCategory(int shopCategoryId);

    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);

    Result<ShopCategory> queryShopCategoryById(int shopCategoryId);

    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
