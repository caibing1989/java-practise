package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class UdpServer {
    public static void main(String[] args) throws IOException {
        // 从服务端接受客户端发送的数据
        DatagramSocket datagramSocket = new DatagramSocket(65001);
        // 存储从客户端接收的内容
        byte[] buff = new byte[100];
        DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
        // 接受客户端发送过来的内容，并将内容封装进DatagramPacket对象中
        datagramSocket.receive(datagramPacket);

        // 从DatagramPacket对象中获取真正存储的数据
        byte[] data = datagramPacket.getData();
        // 将数据从二进制转换成字符串形式
        String content = new String(data, 0, datagramPacket.getLength());
        System.out.println(content);
        // 将要发送给客户端的数据转换成二进制
        byte[] sendContent = String.valueOf(content.length()).getBytes();
        // 服务端给客户端发送数据报
        // 从DatagramPacket对象中获取到数据的来源地址和端口号
        DatagramPacket packetToClient = new DatagramPacket(sendContent, sendContent.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(packetToClient);
    }
}
