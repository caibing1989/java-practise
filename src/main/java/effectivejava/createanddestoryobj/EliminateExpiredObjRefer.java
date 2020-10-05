package effectivejava.createanddestoryobj;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @Description: 消除过期的对象引用
 * @Author: mtdp
 * @Date: 2020-09-26
 */

// 这段程序中并没有很明显的错误，无论如何测试，它都会成功地通过每一项测试
// 但是这段程序有一个内存泄漏问题 unintentional object retention
public class EliminateExpiredObjRefer {
    // Can you spot the memory leak?
    public class Stack {
        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        public Stack() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object e) {
            ensureCapacity();
            elements[size++] = e;
        }

        // 如果一个栈先是增长，然后再收缩，那么，从栈中弹出来的对象将不会被当作垃圾回收
        // 因为栈内部维护着这些对象的过期引用
        public Object pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            return elements[--size];
        }

        public Object pop2() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            Object result = elements[--size];
            elements[size] = null; // Eliminate obsolete reference
            return result;
        }

        /**
         * Ensure space for at least one more element, roughly
         * doubling the capacity each time the array needs to grow
         */
        private void ensureCapacity() {
            if (elements.length == size) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }
    }
}

// 何时应该清空引用呢？Stack类的哪些方面特性使它易于遭受内存泄漏的影响呢？
// 问题在于，Stack类自己管理内存。存储池包含了elements数组的元素。
// 数组活动区域中的元素是已分配的，而数组其余部分的元素则是自由的
// 但是垃圾回收器并不知道这些，对于垃圾回收器而言，数组中的所有对象引用都同等有效
// 只要类是自己管理内存，程序员就应该警惕内存泄漏问题
// 内存泄漏的另一个常见涞源是缓存
