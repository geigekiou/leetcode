package threadtry;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class timedemo2 {
    public static void main(String[] args) {
        ScheduledExecutorService tpool = Executors.newScheduledThreadPool(3);
        tpool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },1000,10000, TimeUnit.MILLISECONDS);
    }
}
