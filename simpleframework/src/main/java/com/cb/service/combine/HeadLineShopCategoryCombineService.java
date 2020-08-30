package com.cb.service.combine;

import com.cb.entity.dto.MainPageInfoDTO;
import com.cb.entity.dto.Result;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
