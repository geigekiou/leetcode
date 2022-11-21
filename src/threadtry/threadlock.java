package threadtry;

class Phone{
    private boolean flag = false;
    public void run(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        synchronized (Phone.this){
                            if (!flag){
                                System.out.println("来电了");
                                flag = true;
                                Phone.this.notify();
                                Phone.this.wait();
                            }
                    }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        synchronized (Phone.this){
                        if (flag == true){
                            System.out.println("接听中，-----完毕了");
                            flag = false;
                            Phone.this.notify();
                            Phone.this.wait();
                        }
                        else {
                            Phone.this.notify();
                            Phone.this.wait();
                        }
                    }
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

public class threadlock {
    public static void main(String[] args) {
        Phone huawei = new Phone();
    }
}

