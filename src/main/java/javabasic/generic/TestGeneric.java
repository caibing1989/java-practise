package javabasic.generic;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public class TestGeneric {
    public static void handelMember(GenericClassExample<? extends Number> integerExample) {
        int result = 111 + (Integer) integerExample.getMember();
        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        GenericClassExample<String> strExample = new GenericClassExample<>("abc");
        GenericClassExample<Number> integerExample = new GenericClassExample<>(123);

        System.out.println(strExample.getClass());

        handelMember(integerExample);
    }
}
