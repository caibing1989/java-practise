package designpattern.responsibilitychain.afteruseultimate;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public interface StudyPrepareFilter {

    public void doFilter(PreparationList preparationList, FilterChain filterChain);
}
