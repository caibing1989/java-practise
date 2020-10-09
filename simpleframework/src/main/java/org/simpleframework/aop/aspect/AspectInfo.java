package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.aop.PointcutLocator;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-06
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspectObject;
    private PointcutLocator pointcutLocator;
}
