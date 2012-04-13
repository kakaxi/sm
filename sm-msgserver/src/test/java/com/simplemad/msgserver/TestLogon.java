package com.simplemad.msgserver;

import com.simplemad.msgserver.runnable.LogonRunnable;


public class TestLogon {
			
	public static void main(String[] args) {
		ThreadExecutor executor = new ThreadExecutor(new LogonRunnable());
		//executor.executeMultiThreads(5, 1000);
		executor.executeSingleThread();
	}
}
