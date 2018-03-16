package pl.mkrzyzanowski.sigmacontroller.util;

/**
 * Created by Michał on 2017-12-03.
 */

public class Timer extends Thread implements Runnable{

    private float time = 12;
    private boolean isPaused;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exc){
                System.out.print("Error Timer Thread");
                return;
            }
            if (!isPaused) time++;
        }
    }

    public float getTime() {
        return time;
    }
    public void setPause(boolean value){
        isPaused = value;
    }

    public boolean getPause() {
        return isPaused;
    }
}
