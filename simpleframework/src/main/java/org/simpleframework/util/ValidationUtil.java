package org.simpleframework.util;

import java.util.Collection;
import java.util.Map;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-04
 */

public class ValidationUtil {
    /**
     * Collection是否为null或size为0
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Collection<?> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
     * String是否为null或""
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(String obj) {
        return obj == null || "".equals(obj);
    }

    /**
     * Array是否为null或者size为0
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    /**
     * Map是否为null或者size为0
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }
}
