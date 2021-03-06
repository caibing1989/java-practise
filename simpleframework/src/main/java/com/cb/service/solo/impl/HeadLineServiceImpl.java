package com.cb.service.solo.impl;

import com.cb.entity.bo.HeadLine;
import com.cb.entity.dto.Result;
import com.cb.service.solo.HeadLineService;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Service
@Slf4j
public class HeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        log.info("addHeadLine被执行了");
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return null;
    }
}
