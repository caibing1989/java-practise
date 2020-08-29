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

public class TcpClient {
    public static void main(String[] args) throws IOException {
        // 创建socket，并指定连接的是本机端口号为65000的服务器socket
        Socket socket = new Socket("127.0.0.1", 65000);

        // 获得socket的输入流
        InputStream inputStream = socket.getInputStream();
        // 获得socket的输出流
        OutputStream outputStream = socket.getOutputStream();

        // 将要传递给Server的字符串转换成byte[]，并将其写入到输出流中
        outputStream.write(new String("Hello World").getBytes());

        // read用来获取数组的长度
        int read = 0;
        // buff主要用来读入输入内容，存成byte数组
        byte[] buff =  new byte[1024];
        read = inputStream.read(buff);

        // 将接受流的byte[]转成字符串，这里获取的内容是服务端发送过来的字符串长度
        String content = new String(buff, 0, read);
        System.out.println("content's length: " + content);

        // 关闭io资源
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
