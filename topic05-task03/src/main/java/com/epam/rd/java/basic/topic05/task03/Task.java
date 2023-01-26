package com.epam.rd.java.basic.topic05.task03;

import java.util.ArrayList;
import java.util.List;

public class Task {

	private int numberOfThreads;

	private int numberOfIterations;

	private int pause;

	private int c1;

	private int c2;

	public Task(int numberOfThreads, int numberOfIterations, int pause) {
		this.numberOfThreads = numberOfThreads;
		this.numberOfIterations = numberOfIterations;
		this.pause = pause;
	}

	public void compare() {
		c1 = 0;
		c2 = 0;
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < numberOfThreads; i++) {
			threads.add(new unsyncCountingThread(c1, c2, pause, numberOfIterations));
		}
		for(var thread : threads) { thread.start(); }

		try {
			for(var thread : threads) { thread.join(); }
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void compareSync() {
		c1 = 0;
		c2 = 0;
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < numberOfThreads; i++) {
			threads.add(new CountingThread(c1, c2, pause, numberOfIterations));
		}
		for(var thread : threads) { thread.start(); }

		try {
			for(var thread : threads) { thread.join(); }
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Task t = new Task(2, 5, 10);
		t.compare();
		System.out.println("~~~");
		t.compareSync();
	}

}
class CountingThread extends Thread {
	static int counter1;
	static int counter2;
	static int pause;
	int iterations;

	CountingThread(int counter1, int counter2, int pause, int iterations) {
		CountingThread.counter1 = counter1;
		this.counter2 = counter2;
		this.pause = pause;
		this.iterations = iterations;
	}

	@Override
	public void run() {
		for (int i = 0; i < iterations; i++) {
			syncedCompare();
		}
	}

	public void Compare() {
		System.out.println((counter1 == counter2) + " " + counter1 + " " + counter2);
		counter1++;
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		counter2++;
	}

	public static synchronized void syncedCompare() {
		System.out.println((counter1 == counter2) + " " + counter1 + " " + counter2);
		counter1++;
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		counter2++;
	}
}
class unsyncCountingThread extends Thread {
	static int counter1;
	static int counter2;
	static int pause;
	int iterations;

	unsyncCountingThread(int counter1, int counter2, int pause, int iterations) {
		unsyncCountingThread.counter1 = counter1;
		unsyncCountingThread.counter2 = counter2;
		unsyncCountingThread.pause = pause;
		this.iterations = iterations;
	}

	@Override
	public void run() {
		for (int i = 0; i < iterations; i++) {
			syncedCompare();
		}
	}

	public void Compare() {
		System.out.println((counter1 == counter2) + " " + counter1 + " " + counter2);
		counter1++;
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		counter2++;
	}

	public static void syncedCompare() {
		System.out.println((counter1 == counter2) + " " + counter1 + " " + counter2);
		counter1++;
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		counter2++;
	}
}
