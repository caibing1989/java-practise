package effectivejava.createanddestoryobj.build.nutritionfacts;

/**
 * @Description: JavaBeans Pattern - allows inconsistency, mandates mutability
 * 这种模式弥补了重叠构造器模式的不足，创建实例很容易，代码读起来也很容易。
 * 但是，JavaBeans模式自身有着很严重的缺点，因为构造过程被分到几个调用中，在构造过程中JavaBean可能处于不一致的状态
 * JavaBeans模式使得把类做成不可变的可能性不复存在，这就需要程序员付出额外的努力来确保它的线程安全
 * @Author: mtdp
 * @Date: 2020-09-24
 */

public class NutritionFactsJavaBeansPattern {
    // Parameters initialized to default values (if any)
    private int servingSize     = -1;       // Required; no default value
    private int servings        = -1;       // Required; no default value
    private int calories        = 0;
    private int fat             = 0;
    private int sodium          = 0;
    private int carbohydrate    = 0;

    public NutritionFactsJavaBeansPattern() {}
    // Setters
    public void setServingSize(int val) {servingSize = val;}
    public void setServings(int val) {servings = val;}
    public void setCalories(int val) {calories = val;}
    public void setFat(int val) {fat = val;}
    public void setSodium(int val) {sodium = val;}
    public void setCarbohydrate(int val) {carbohydrate = val;}
}
