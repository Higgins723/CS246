/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadassignment;

class secondFile implements Runnable {

    private volatile Thread running;


    public void stopRunning() {

        running = null;

        System.out.println("All Finished");

    }




    @Override
    public void run() {

        Thread thisThread = Thread.currentThread();

        while(running == thisThread) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {

        }
        System.out.println("Running...");
    }

    }

}
