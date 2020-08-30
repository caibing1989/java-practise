package javabasic.generic;

import java.util.Random;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public class RobotFactory implements GenericIFactory<String, Integer> {
    private String[] stringRobot = new String[]{"Hello", "Hi"};
    private Integer[] integerRobot = new Integer[]{111, 0};

    @Override
    public String nextObject() {
        return stringRobot[new Random().nextInt(2)];
    }

    @Override
    public Integer nextNumber() {
        return integerRobot[new Random().nextInt(2)];
    }

    public static void main(String[] args) {
        GenericIFactory<String, Integer> factory = new RobotFactory();
        System.out.println("nextObject: " + factory.nextObject());
        System.out.println("nextNumber: " + factory.nextNumber());
    }
}
