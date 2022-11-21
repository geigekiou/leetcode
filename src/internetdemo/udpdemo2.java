package internetdemo;

import java.net.*;

public class udpdemo2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket sersocket = new DatagramSocket(8888);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
        sersocket.receive(packet);
        System.out.println(new String(buffer));
        System.out.println(packet.getSocketAddress().toString());
        sersocket.close();

        MulticastSocket socket = new MulticastSocket(9999);
        socket.joinGroup(InetAddress.getByName("224.0.1.1"));
    }
}
