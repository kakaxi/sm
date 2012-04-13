package com.simplemad.msgserver;

import com.simplemad.msgserver.runnable.AdRunnable;


public class TestAd {
		
	public static void main(String[] args) {
		ThreadExecutor executor = new ThreadExecutor(new AdRunnable());
		executor.executeMultiThreads(5, 1);
	}

}
