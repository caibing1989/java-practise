package designpattern.responsibilitychain.afteruse;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description: 洗头
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class WashHairFilter extends AbstractPrepareFilter {
    public WashHairFilter(AbstractPrepareFilter nextPrepareFilter) {
        super(nextPrepareFilter);
    }

    @Override
    public void prepare(PreparationList preparationList) {
        if (preparationList.isWashHair()) {
            System.out.println("洗头");
        }
    }
}
