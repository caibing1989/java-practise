package effectivejava.createanddestoryobj.build.pizza;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Description: Build pattern for class hierarchies
 * @Author: mtdp
 * @Date: 2020-09-25
 */

public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    // 泛型，带有一个递归类型参数
    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // Subclasses must override this method to return "this"
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone(); // See Item 50
    }
}
