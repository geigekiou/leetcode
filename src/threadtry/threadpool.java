package threadtry;

import java.util.concurrent.*;

public class threadpool {
    public static void main(String[] args) {
//        ExecutorService tpool = new ThreadPoolExecutor(3,5,
//                30,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(3),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
//        Future<Integer> f1 =  tpool.submit(new thread2());
        ExecutorService epool = Executors.newFixedThreadPool(3);
        return;
    }
}
