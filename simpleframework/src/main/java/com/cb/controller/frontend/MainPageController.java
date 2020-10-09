package com.cb.controller.frontend;

import com.cb.entity.dto.MainPageInfoDTO;
import com.cb.entity.dto.Result;
import com.cb.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Controller
@Getter
public class MainPageController {
    @Autowired
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}
