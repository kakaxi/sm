package com.simplemad.msgserver;

import com.simplemad.msgserver.runnable.AdEffectRunnable;


public class TestAdEffect {
	
	public static void main(String[] args) {
		ThreadExecutor executor = new ThreadExecutor(new AdEffectRunnable());
		executor.executeMultiThreads(5, 10000);
	}
}
