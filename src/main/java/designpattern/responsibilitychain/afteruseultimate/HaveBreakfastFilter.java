package designpattern.responsibilitychain.afteruseultimate;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class HaveBreakfastFilter implements StudyPrepareFilter {
    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        if (preparationList.isHaveBreakfast()) {
            System.out.println("吃早餐");
        }

        filterChain.doFilter(preparationList, filterChain);
    }
}
