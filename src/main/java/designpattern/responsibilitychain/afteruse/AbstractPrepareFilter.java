package designpattern.responsibilitychain.afteruse;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description: 链上的每个对象都持有下一个对象的引用
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public abstract class AbstractPrepareFilter {
    private AbstractPrepareFilter nextPrepareFilter;

    public AbstractPrepareFilter(AbstractPrepareFilter nextPrepareFilter) {
        this.nextPrepareFilter = nextPrepareFilter;
    }

    public void doFilter(PreparationList preparationList, Study study) {
        prepare(preparationList);

        if (nextPrepareFilter == null) {
            study.study();
        } else {
            nextPrepareFilter.doFilter(preparationList, study);
        }
    }

    public abstract void prepare(PreparationList preparationList);
}
