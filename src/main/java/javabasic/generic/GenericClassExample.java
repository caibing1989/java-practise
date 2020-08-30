package javabasic.generic;

import lombok.Data;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Data
public class GenericClassExample<T> {
    private T member;

    public T getMember() {
        return member;
    }

    public GenericClassExample(T member) {
        this.member = member;
    }

    public T handleSomething(T target) {
        return target;
    }

    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s", element);
            System.out.print(" ");
        }
        System.out.println();
    }
}
