package threadtry;

public class threadtry {
    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("子线程第" + i + "次");
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程第"+ i +"次");
        }
    }
}
