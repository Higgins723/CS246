package threadassignment;

class thirdFile implements Runnable {

       public int i = 2;
    @Override
    public void run() {

        while (i <= 100) {

            System.out.println(i);
            i = i + 2;

        try {
           Thread.sleep(100);
       } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        }
        secondFile run = new secondFile();
        run.stopRunning();
    }
}
