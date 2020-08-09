package bitmap;

import bitmap.constant.CommonConstant;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-06-21
 */

public class SplitFileHandle {
    public static void main(String[] args) throws InterruptedException {
        final int splitSize = 8;
        CountDownLatch countDownLatch = new CountDownLatch(splitSize);
        long start = System.currentTimeMillis();

        for (int i = 0; i < splitSize; i++) {
            final int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    File splitFiler = new File("/Users/mtdp/split/original-data".concat("_").concat(String.valueOf(finalI)));
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(splitFiler));
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(CommonConstant.file));

                    String contentLine;
                    while ((contentLine = bufferedReader.readLine()) != null) {
                        int value = Integer.parseInt(contentLine);

                        if (value % splitSize == finalI) {
                            bufferedWriter.append(Integer.toString(value)).append("\n");
                        }
                    }
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    System.out.println("分片" + finalI + "拆分完成");
                    countDownLatch.countDown();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        countDownLatch.await();

        long end = System.currentTimeMillis();
        System.out.println("所有的分片拆分完成，耗时" + (end - start));
    }
}
