package socket.udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) throws Exception {
        // 1. 创建服务端对象
        DatagramSocket socket = new DatagramSocket(8080);

        // 2. 创建数据包对象，用于接收数据包
        byte []bytes = new byte[1024 * 64];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        // 3. 接收客户端发来的数据
        socket.receive(datagramPacket);

        // 4. 读取接收到的字节数组
        System.out.println(new String(bytes, 0, datagramPacket.getLength()));
    }
}
