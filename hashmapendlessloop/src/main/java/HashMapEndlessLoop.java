import java.util.HashMap;

/**
 * @Description: 演示HashMap在多线程的情况下，造成死循环的情况，在循环扩容的时候时候，容易导致循环链表，造成死循环
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class HashMapEndlessLoop {
    private static HashMap<Integer, String> hashMap = new HashMap<>(2, 1.5f);

    public static void main(String[] args) {
        // 1 3 5 这三个key，可以落到同一个桶中去，以便让hashmap发生扩容
        hashMap.put(5, "C");
        hashMap.put(7, "B");
        hashMap.put(3, "D");

        new Thread(new Runnable() {
            @Override
            public void run() {
                hashMap.put(15, "M");
                System.out.println(hashMap);
            }
        }, "Thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                hashMap.put(1, "E");
                System.out.println(hashMap);
            }
        }, "Thread2").start();
    }
}
