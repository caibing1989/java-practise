package threadcoreknowledge.threadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 构造函数使用thread初始化，导致线程安全问题，通常这种场景发生在不知情的情况下，例如连接池的创建
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class UserThreadInConstruct {

    private Map<String, String> myMap;

    private UserThreadInConstruct() {
        new Thread(() -> {
            myMap = new HashMap<>();
            myMap.put("1", "周一");
            myMap.put("2", "周二");
            myMap.put("3", "周三");
            myMap.put("4", "周四");
            myMap.put("5", "周五");
        }).start();
    }

    // 存在风险，外部可能会篡改该对象集合
    Map<String, String> getMyMap() {
        return myMap;
    }

    public static void main(String[] args) {
        // 会发生空指针异常
        System.out.println("myMap中，key=1， value=" + new UserThreadInConstruct().getMyMap().get("1"));
    }
}
