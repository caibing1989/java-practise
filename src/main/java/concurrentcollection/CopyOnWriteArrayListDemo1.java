package concurrentcollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 演示CopyOnWriteArrayList在迭代的过程中可以修改数组内容，但是ArrayList不行
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class CopyOnWriteArrayListDemo1 {

    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(list);
            String s = iterator.next();
            System.out.println("s:" + s);

            if (s.equals("2")) {
                list.remove("5");
            }
            if (s.equals("3")) {
                list.add("3 found");
            }
        }
    }
}
