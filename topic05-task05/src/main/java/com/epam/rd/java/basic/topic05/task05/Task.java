package com.epam.rd.java.basic.topic05.task05;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
	public static final String FILE_NAME = "data.txt";
	public static final int separatorOfLength = System.lineSeparator().length();
	private static RandomAccessFile raf;
	public static void createRAF(int numberOfThreads, int numberOfIterations, int pause) {
		try {
			raf = new RandomAccessFile(FILE_NAME, "rw");
			raf.setLength(0);
			Thread[] arrayOfThreads = new Thread[numberOfThreads];
			for (AtomicInteger i = new AtomicInteger(0); i.get() < numberOfThreads; i.incrementAndGet()) {
				arrayOfThreads[i.get()] = new Thread(new Runnable() {
					final int index = i.get();
					@Override
					public void run() {
						try {
							for (int j = 0; j < numberOfIterations; ++j) {

								synchronizedWrite(j, index, numberOfIterations);

								if (j == numberOfIterations - 1) {
									synchronizedWrite(numberOfIterations, index, numberOfIterations);
								}
								Thread.sleep(pause);
							}
						} catch (IOException | InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				});
			}
			for (int i = 0; i < numberOfThreads; ++i) {
				arrayOfThreads[i].start();
			}
			for (int i = 0; i < numberOfThreads; ++i) {
				arrayOfThreads[i].join();
			}
			raf.close();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public static synchronized void synchronizedWrite(int iteration, int idxOfLine, int numberOfIterations) throws IOException {
		raf.seek((long) idxOfLine * (numberOfIterations + separatorOfLength) + iteration);
		if (iteration == numberOfIterations) {
			raf.write(System.lineSeparator().getBytes());
		} else {
			raf.write('0' + idxOfLine);
		}
	}
	public static void main(String[] args) throws IOException {
		createRAF(5, 20, 1000);
		Files.readAllLines(Paths.get(FILE_NAME))
				.stream()
				.forEach(System.out::println);
	}
}