package com.epam.rd.java.basic.topic05.task03;

public class NumberOfThreads extends Thread{
    static int firstCounter;
    static int secondCounter;
    static int pause;
    int i;

    public NumberOfThreads(int firstCounter, int secondCounter, int pause, int i) {
        NumberOfThreads.firstCounter = firstCounter;
        NumberOfThreads.secondCounter = secondCounter;
        NumberOfThreads.pause = pause;
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
    public static synchronized void synchronizedCompare()
    {
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
    public void compare()
    {
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
    @Override
    public void run()
    {
        for (int j = 0; j < this.i; ++j)
        {
            synchronizedCompare();
        }
    }

    private static void comp()
    {
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

}
