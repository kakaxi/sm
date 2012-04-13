package com.simplemad.msgserver;

import com.simplemad.msgserver.runnable.RegisterRunnable;


public class TestRegister {
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		System.out.println("begin: " + begin);

		ThreadExecutor executor = new ThreadExecutor(new RegisterRunnable());
		executor.executeSingleThread();

		long end = System.currentTimeMillis();
		System.out.println("end: " + end);

		System.out.println("used " + (end - begin) / 1000 + " seconds");
	}

}
