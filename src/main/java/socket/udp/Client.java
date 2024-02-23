package socket.udp;

import java.io.IOException;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            // 1. 创建客户端对象
            DatagramSocket socket;
            socket = new DatagramSocket();

            // 2. 创建数据包对象，包含要发出去的消息
            byte[] bytes = "test".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8080);

            // 3. 发送数据包
            socket.send(datagramPacket);
            System.out.println("发送成功");
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
