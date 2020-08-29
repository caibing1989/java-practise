package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-26
 */

public class TcpServer {
    public static void main(String[] args) throws IOException {
        // 创建serverSocket，并将socket绑定到65000端口
        ServerSocket serverSocket = new ServerSocket(65000);

        // 一直等待并处理客户端发送过来的请求
        while (true) {
            // 监听客户端，知道客户端返回连接信息才返回
            Socket socket = serverSocket.accept();
            // 获取客户端的请求信息后，执行相关业务逻辑
            new LengthCalculator(socket).start();
        }
    }
}
