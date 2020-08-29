package designpattern.responsibilitychain.beforeuse;

/**
 * @Description: 小明要去上学，妈妈给小明列了一些上学前需要做的清单（洗头、吃早饭、洗脸），
 * 小明必须按照妈妈的要求，把清单上打钩的事情做完了才可以上学
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class PreparationList {
    // 是否洗脸
    private boolean washFace;

    // 是否洗头
    private boolean washHair;

    // 是否吃早餐
    private boolean haveBreakfast;

    public boolean isWashFace() {
        return washFace;
    }

    public void setWashFace(boolean washFace) {
        this.washFace = washFace;
    }

    public boolean isWashHair() {
        return washHair;
    }

    public void setWashHair(boolean washHair) {
        this.washHair = washHair;
    }

    public boolean isHaveBreakfast() {
        return haveBreakfast;
    }

    public void setHaveBreakfast(boolean haveBreakfast) {
        this.haveBreakfast = haveBreakfast;
    }

    @Override
    public String toString() {
        return "PreparationList{" +
                "washFace=" + washFace +
                ", washHair=" + washHair +
                ", haveBreakfast=" + haveBreakfast +
                '}';
    }
}
