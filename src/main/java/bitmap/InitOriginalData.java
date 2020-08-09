package bitmap;

import bitmap.constant.CommonConstant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static bitmap.constant.CommonConstant.TOTAL_COUNT;


/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-06-21
 */

public class InitOriginalData {
    public static void main(String[] args) throws IOException {
        // 创建文件
        CommonConstant.file.createNewFile();
        //创建一个字符缓冲输出流对象
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CommonConstant.file)) ;

        for (int i = 0; i < TOTAL_COUNT; i++) {
            // 尾数为9的不写入，用于测试不存在的情况
            if (i % 10 != 9) {
                bufferedWriter.append(Integer.toString(i)).append("\n");
            }
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
