package internetdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class udpdemo {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientsocket = new DatagramSocket();
        byte[] buffer = "好好学习天天向上".getBytes();
        DatagramPacket clientudppacket = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(),8888);
        clientsocket.send(clientudppacket);
        clientsocket.close();
    }
}
