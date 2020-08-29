package designpattern.responsibilitychain.afteruseultimate;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class WashHairFilter implements StudyPrepareFilter {
    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        if (preparationList.isWashHair()) {
            System.out.println("洗头");
        }

        filterChain.doFilter(preparationList, filterChain);
    }
}
