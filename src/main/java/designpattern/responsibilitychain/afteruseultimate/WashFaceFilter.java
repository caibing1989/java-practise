package designpattern.responsibilitychain.afteruseultimate;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class WashFaceFilter implements StudyPrepareFilter {
    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        if (preparationList.isWashFace()) {
            System.out.println("洗脸");
        }

        filterChain.doFilter(preparationList, filterChain);
    }
}
