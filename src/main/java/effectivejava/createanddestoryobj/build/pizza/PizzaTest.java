package effectivejava.createanddestoryobj.build.pizza;

import static effectivejava.createanddestoryobj.build.pizza.MyPizza.Size.SMALL;
import static effectivejava.createanddestoryobj.build.pizza.Pizza.Topping.*;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-25
 */

public class PizzaTest {
    public static void main(String[] args) {
        MyPizza pizza = new MyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();

        Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
    }
}
