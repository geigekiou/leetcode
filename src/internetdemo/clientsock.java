package internetdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class clientsock {
    public static void main(String[] args) {
        try {
            Socket clientsocket = new Socket("127.0.0.1",7878);
            OutputStream cos = clientsocket.getOutputStream();
            PrintStream ps = new PrintStream(cos);
            while(true){
                System.out.println("your input here");
                Scanner sc = new Scanner(System.in);
                ps.println("客户端："+sc.nextLine());
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
