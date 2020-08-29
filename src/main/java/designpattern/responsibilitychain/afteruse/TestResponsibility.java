package designpattern.responsibilitychain.afteruse;

import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class TestResponsibility {

    public static void main(String[] args) {
        PreparationList preparationList = new PreparationList();
        preparationList.setWashFace(true);
        preparationList.setWashHair(false);
        preparationList.setHaveBreakfast(true);

        Study study = new Study();

        AbstractPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter(null);
        AbstractPrepareFilter washFaceFilter = new WashFaceFilter(haveBreakfastFilter);
        AbstractPrepareFilter washHairFilter = new WashHairFilter(washFaceFilter);

        washHairFilter.doFilter(preparationList, study);
    }
}

/**
 * 个人认为这种写法虽然符合开闭原则，但是两个明显的缺点对客户端并不友好：
 * 1、增加、减少责任链对象，需要修改客户端代码，即比如我这边想要增加一个打扫屋子的操作，那么testResponsibility()方法需要改动
 * 2、AbstractPrepareFilter washFaceFilter = new WashFaceFilter(haveBreakfastFilter)这种调用方式不够优雅，客户端需要思考一下，到底真正调用的时候调用三个Filter中的哪个Filter
 *
 */
