package effectivejava.createanddestoryobj.build.pizza;

import java.util.Objects;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-25
 */

public class MyPizza extends Pizza {
    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        // 子类方法声明返回超级类中声明的返回类型的子类型，被称作协变返回类型-covariant return type
        @Override
        public MyPizza build() {
            return new MyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private MyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
