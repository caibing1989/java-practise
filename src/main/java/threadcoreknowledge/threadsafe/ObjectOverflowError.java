package threadcoreknowledge.threadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 方法返回一个private对象（private本意是不让外部使用），需要通过副本的方式解决
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class ObjectOverflowError {

    private Map<String, String> myMap;

    private ObjectOverflowError() {
        myMap = new HashMap<>();
        myMap.put("1", "周一");
        myMap.put("2", "周二");
        myMap.put("3", "周三");
        myMap.put("4", "周四");
        myMap.put("5", "周五");
    }

    // 存在风险，外部可能会篡改该对象集合
    Map<String, String> getMyMap() {
        return myMap;
    }

    // 该方法提供private对象的副本，保证线程安全
    Map<String, String> getMyMapImprove() {
        return new HashMap<>(myMap);
    }

    public static void main(String[] args) {
        ObjectOverflowError objectOverflowError = new ObjectOverflowError();
        objectOverflowError.getMyMapImprove().put("1", null);

        System.out.println(objectOverflowError.getMyMap().get("1"));
    }
}
