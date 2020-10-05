package effectivejava.createanddestoryobj.avoidunnecessaryobj;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-26
 */

public class CreateUnnecessaryObj {
    public static void main(String[] args) {
        // 该语句每次被执行的时候都创建一个新的String实例，但是这些创建对象的动作全都是不必需要的
        // 传递给String构造器的参数"bikini"本身就是一个String实例，功能方面等同于构造器创建的所有对象
        // 如果这种用法是在一个循环中，或者是在一个被频繁调用的方法中，就会创建出成千上万不必要的String实例
        String s1 = new String("bikini");  // don't do this

        // 它可以保证，对于所有在同一台虚拟机中运行的代码，只要它们包含相同的字符串字面常量，该对象就会被重用
        String s2 = "bikini"; // should do this

        // 静态工厂方法不会每次创建一个新的对象
        Boolean b1 = Boolean.valueOf("true");  // should do this

        // 构造器在每次被调用的时候都会创建一个新的对象
        Boolean b2 = new Boolean("true");  // don't do this
    }

    // Performance can be greatly improved!
    static boolean isRomanNumeral(String str) {
        // 使用正则表达式来确定一个字符是否为一个有效的罗马数字
        // 这个实现的问题在于它依赖String.matches方法
        // 虽然String.matches方法最易于查看一个字符串是否与正则表达式相匹配，但并不适合在注重性能的场景中重复使用
        // 问题在于，它在内部为正则表达式创建了一个Pattern实例，却只用了一次，之后就可以进行垃圾回收了
        // 创建Pattern实例的成本很高，因为需要将正则表达式编译成一个有限状态机 finite state machine
        return str.matches("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // 为了提升性能，应该显式地将正则表达式编译成一个Pattern实例，让它成为类初始化的一部分，并将它缓存起来
    // 每次调用isRomanNumeral方法的时候就重用同一个实例
    public static class RomanNumberals {
        private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

        static boolean isRomanNumeral(String str) {
            return ROMAN.matcher(str).matches();
        }
    }

    // Hideously slow! Can you spot the object creation?
    // 这段程序算出的答案是正确的，但是比实际情况要更慢一些，只因为打错了一个字符
    // 变量sum被声明成Long而不是long，意味着程序构造了大约2的31次方个多余的Long实例
    // 大约每次往Long sum中增加long时构造一个实例
    // 要优先使用基本类型而不是装箱基本类型，要当心无意识的自动装箱
    private static long sum() {
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
