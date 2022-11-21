package threadtry;

import java.util.Timer;
import java.util.TimerTask;

public class timedemo {
    public static void main(String[] args) {
        Timer tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },3000,10000);
    }
}
