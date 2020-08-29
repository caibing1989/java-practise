package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-26
 */

public class LengthCalculator extends Thread {
    private Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            // 获得socket的输入流
            InputStream inputStream = socket.getInputStream();
            // 获得socket的输出流
            OutputStream outputStream = socket.getOutputStream();

            // read用来获取数组的长度
            int read = 0;
            // buff主要用来读入输入内容，存成byte数组
            byte[] buff =  new byte[1024];
            read = inputStream.read(buff);

            // 将接受流的byte[]转成字符串，这里获取的内容是客户端发送过来的字符串参数
            String content = new String(buff, 0, read);
            System.out.println("content: " + content);

            // 往输出流中写入获得的字符串的长度，并发回给客户端
            outputStream.write(String.valueOf(content.length()).getBytes());

            // 关闭io资源
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
