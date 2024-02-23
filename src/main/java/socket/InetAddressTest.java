package socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());

        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostAddress());

        try {
            System.out.println(ip2.isReachable(6000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
