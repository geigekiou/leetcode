package threadtry;

import java.util.concurrent.Callable;

public class thread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum+=i;
        }
        Thread.sleep(5000);
        System.out.println(sum);
        return sum;
    }
}
