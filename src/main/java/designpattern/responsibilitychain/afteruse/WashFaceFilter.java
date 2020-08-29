package designpattern.responsibilitychain.afteruse;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description: 洗脸
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class WashFaceFilter extends AbstractPrepareFilter {
    public WashFaceFilter(AbstractPrepareFilter nextPrepareFilter) {
        super(nextPrepareFilter);
    }

    @Override
    public void prepare(PreparationList preparationList) {
        if (preparationList.isWashFace()) {
            System.out.println("洗脸");
        }
    }
}
