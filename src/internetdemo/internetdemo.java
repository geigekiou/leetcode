package internetdemo;

import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class internetdemo {
    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getLocalHost() ;
        System.out.println(ip.toString());
        InetAddress ip2 = InetAddress.getByName("www.mihoyo.com") ;
        System.out.println(ip2.getHostAddress());
        System.out.println(ip2.getHostName());
        System.out.println(ip2.isReachable(5000));

    }
}
