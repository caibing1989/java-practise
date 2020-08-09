package jvm.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 监控内存泄漏
 * @Author: mtdp
 * @Date: 2020-08-08
 */

public class MemoryLeak {
    public static void main(String[] args) throws InterruptedException, IOException {
        Thread.sleep(10000L);

        List list = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(new A());

            if (i % 20 == 0) {
                Thread.sleep(100L);
            }
        }

        System.out.println("over=========>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
    }
}
class A {
    private byte[] bytes = new byte[10 * 1024];
}
