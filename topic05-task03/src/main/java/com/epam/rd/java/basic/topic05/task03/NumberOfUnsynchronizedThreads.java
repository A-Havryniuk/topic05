package com.epam.rd.java.basic.topic05.task03;

public class NumberOfUnsynchronizedThreads extends Thread {
    private static int firstCounter;
    private static int secondCounter;
    private static int pause;
    private int i;

    public NumberOfUnsynchronizedThreads(int firstCounter, int secondCounter, int pause, int i) {
        NumberOfUnsynchronizedThreads.firstCounter = firstCounter;
        NumberOfUnsynchronizedThreads.secondCounter = secondCounter;
        NumberOfUnsynchronizedThreads.pause = pause;
        this.i = i;
    }

    public int getFirstCounter() {
        return firstCounter;
    }

    public int getSecondCounter() {
        return secondCounter;
    }

    public int getPause() {
        return pause;
    }

    public int getI() {
        return i;
    }

    @Override
    public void run() {
        for (int j = 0; i < i; j++) {
            syncedCompare();
        }
    }

    public static void syncedCompare() {
        System.out.println((firstCounter == secondCounter) +
                " " + firstCounter + " " + secondCounter);
        firstCounter++;
        try {
            Thread.sleep(pause);
        } catch(InterruptedException ex)
        {
            throw new RuntimeException(ex);
        }
        secondCounter++;
    }

    public void Compare() {
        System.out.println((firstCounter == secondCounter) +
                " " + firstCounter + " " + secondCounter);
        firstCounter++;
        try {
            Thread.sleep(pause);
        } catch(InterruptedException ex)
        {
            throw new RuntimeException(ex);
        }
        secondCounter++;
    }

    private static void comp()
    {
        System.out.println((firstCounter == secondCounter) + " " + firstCounter + " " + secondCounter);
        ++firstCounter;
        try {
            Thread.sleep(pause);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        ++secondCounter;
    }
}
