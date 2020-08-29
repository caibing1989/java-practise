package socket;

import java.io.IOException;
import java.net.*;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class UdpClient {
    public static void main(String[] args) throws IOException {
        // 客户端发送数据给服务端
        DatagramSocket datagramSocket = new DatagramSocket();
        // 要发送给服务端的数据
        byte[] buff = "Hello World".getBytes();
        // 将IP封装成InetAddress对象
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        // 将要发送给服务端的数据封装成DatagramPacket对象，需要填上ip地址和端口号
        DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length, inetAddress, 65001);
        // 发送数据给服务端
        datagramSocket.send(datagramPacket);

        // 客户端接收服务端发送过来的数据报
        byte[] data = new byte[100];
        // 创建DatagramPacket对象用来存储服务端发送过来的数据
        DatagramPacket receivePacket = new DatagramPacket(data, data.length);
        // 将接收的数据存储到DatagramPacket对象中
        datagramSocket.receive(receivePacket);
        // 将服务器端发送过来的数据取出来并打印到控制台
        String content = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(content);
    }
}
