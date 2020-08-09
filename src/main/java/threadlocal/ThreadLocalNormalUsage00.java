package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 用两个线程分别打印出生日信息
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class ThreadLocalNormalUsage00 {
    public static void main(String[] args) {
        new Thread(() -> {
            String date = new ThreadLocalNormalUsage00().date(10);
            System.out.println(date);
        }).start();

        new Thread(() -> {
            String date = new ThreadLocalNormalUsage00().date(104707);
            System.out.println(date);
        }).start();
    }

    public String date(int seconds) {
        // 参数的单位为毫秒，从1970.01.01 00:00:00开始
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
