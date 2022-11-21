package threadtry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class mythread implements Callable<Integer> {
    private int num;
    public mythread(int n){
        this.num = n;
    }
    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i = 0; i < num; i++) {
            sum +=i;
        }
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(10000);
        return sum;
    }
}

public class threaddemo{
    public static void main(String[] args) throws Exception{
        Callable<Integer> callback = new mythread(100);
        FutureTask<Integer> f1 = new FutureTask<>(callback);
        new Thread(f1,"your thread-01").start();
        System.out.println("运行结果=" + f1.get());
    }
}
