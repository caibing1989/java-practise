package effectivejava.createanddestoryobj.build.nutritionfacts;

/**
 * @Description: Builder Pattern，它既能保证像重叠构造器模式那样的安全性，也能保证像JavaBeans模式那么好的可读性
 * @Author: mtdp
 * @Date: 2020-09-24
 */

public class NutritionFactsBuilderPattern {
    private final int servingSize;      // (ml)                 required
    private final int servings;         // (per container)      required
    private final int calories;         // (per serving)        optional
    private final int fat;              // (g/serving)          optional
    private final int sodium;           // (mg/serving)         optional
    private final int carbohydrate;     // (g/serving)          optional

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories        = 0;
        private int fat             = 0;
        private int sodium          = 0;
        private int carbohydrate    = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFactsBuilderPattern build() {
            return new NutritionFactsBuilderPattern(this);
        }
    }

    private NutritionFactsBuilderPattern(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        // 这样的客户端代码很容易编写，更为重要的是易于阅读，Builder模式模拟了具名的可选参数，就像Python和Scala编程语言中的一样
        NutritionFactsBuilderPattern cocaCola = new NutritionFactsBuilderPattern.Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
    }
}
