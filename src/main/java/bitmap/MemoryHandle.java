package bitmap;

import bitmap.constant.CommonConstant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static bitmap.constant.CommonConstant.TOTAL_COUNT;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-06-21
 */

public class MemoryHandle {

    public static void main(String[] args) throws IOException {
        // 初始化数组时，即分配内存
        int[] array = new int[TOTAL_COUNT];

        BufferedReader bufferedReader = new BufferedReader(new FileReader(CommonConstant.file));

        String contentLine;
        int index = 0;
        while ((contentLine = bufferedReader.readLine()) != null) {
            int value = Integer.parseInt(contentLine);

            array[index++] = value;
        }
        System.out.println("载入完成");

        int toCheck = 23712873;
        for (int i : array) {
            if (i == toCheck) {
                System.out.println("包含" + toCheck);
                return;
            }
        }
        System.out.println("不包含" + toCheck);
    }
}
