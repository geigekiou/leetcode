package internetdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class serverdemo {
    private static ExecutorService pool = new ThreadPoolExecutor(2,3,6, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        try {
            ServerSocket sscoket = new ServerSocket(7878);
            System.out.println("server启动成功~~~");
            while(true){
                Socket socket = sscoket.accept();
                Runnable target = new ServerRunnable(socket);
                pool.execute(target);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
