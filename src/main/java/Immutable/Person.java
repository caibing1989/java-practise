package Immutable;

/**
 * @Description: 不可变的对象，其它类无法修改这个对象，即使是public，不可变的对象一定是线程安全的
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class Person {

     final int age = 18;
     final String name = "Alice";
}

class Test {
    public static void main(String[] args) {
        Person person = new Person();
//        person.age = 10;
    }
}
