package com.cb.service.solo;

import com.cb.entity.bo.HeadLine;
import com.cb.entity.dto.Result;

import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public interface HeadLineService {
    Result<Boolean> addHeadLine(HeadLine headLine);

    Result<Boolean> removeHeadLine(int headLineId);

    Result<Boolean> modifyHeadLine(HeadLine headLine);

    Result<HeadLine> queryHeadLineById(int headLineId);

    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
