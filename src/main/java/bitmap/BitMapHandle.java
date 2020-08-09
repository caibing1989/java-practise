package bitmap;

import bitmap.constant.CommonConstant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-06-21
 */

public class BitMapHandle {
    public static void main(String[] args) throws IOException {

        int[] bitmapArray = new int[Integer.MAX_VALUE / 32 + 1];
        BufferedReader bufferedReader = new BufferedReader(new FileReader(CommonConstant.file));

        String contentLine;
        int index = 0;
        int bitIndex = 0;
        while ((contentLine = bufferedReader.readLine()) != null) {
            int value = Integer.parseInt(contentLine);

            // index = value/32
            index = value >> 5;
            // bitIndex = value%32
            bitIndex = value & 0x1f;

            bitmapArray[index] = 1 << bitIndex;
        }
        System.out.println("载入完成");

        bitmapCheck(bitmapArray,45);
        bitmapCheck(bitmapArray, 23712879);
        bitmapCheck(bitmapArray, Integer.MAX_VALUE);
    }

    private static void bitmapCheck(int[] bitmapArray, int toCheck) {
        long start = System.currentTimeMillis();

        int checkIndex = toCheck >> 5;
        int checkBitIndex = toCheck & 0x1f;

        if ((bitmapArray[checkIndex] & 1 << checkBitIndex) != 0) {
            long end = System.currentTimeMillis();
            System.out.println("包含" + toCheck + ", 耗时" + (end - start));
        } else {
            long end = System.currentTimeMillis();
            System.out.println("不包含" + toCheck + ", 耗时" + (end - start));
        }
    }
}
