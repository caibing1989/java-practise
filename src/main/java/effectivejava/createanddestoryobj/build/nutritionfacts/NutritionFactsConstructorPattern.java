package effectivejava.createanddestoryobj.build.nutritionfacts;

/**
 * @Description: 重叠构造器可行，但是当有许多参数的时候，客户端代码会很难编写，并且仍然较难阅读
 * Telescoping constructor pattern - does not scale well!
 * @Author: mtdp
 * @Date: 2020-09-24
 */

public class NutritionFactsConstructorPattern {
    private final int servingSize;      // (ml)                 required
    private final int servings;         // (per container)      required
    private final int calories;         // (per serving)        optional
    private final int fat;              // (g/serving)          optional
    private final int sodium;           // (mg/serving)         optional
    private final int carbohydrate;     // (g/serving)          optional

    public NutritionFactsConstructorPattern(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFactsConstructorPattern(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFactsConstructorPattern(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFactsConstructorPattern(int servingSize, int servings, int calories,
                                            int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFactsConstructorPattern(int servingSize, int servings, int calories,
                                            int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
