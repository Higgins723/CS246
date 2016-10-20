package threadassignment;

public class ThreadAssignment {
    public static void main(String[] args) throws InterruptedException {
        Runnable counterOdd  = new MyRunnable();
        Runnable counterEven = new thirdFile();
        Runnable displayRunning = new secondFile();
        Thread t1 = new Thread(counterOdd);
        Thread t2 = new Thread(counterEven);
        Thread t3 = new Thread(displayRunning);

        t1.start();
        t2.start();
        t3.start();

    }


}
