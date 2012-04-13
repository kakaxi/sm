package com.simplemad.msgserver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplemad.msgserver.runnable.IRunnable;

public class ThreadExecutor {
	private final Logger logger = LoggerFactory.getLogger(ThreadExecutor.class);	
	
	private IRunnable run;
	
	public ThreadExecutor(IRunnable run) {
		this.run = run;
	}
	
	public void executeSingleThread() {
		new Thread(run.getRunnable()).start();
	}
	
	public void executeMultiThreads(int threads) {
		for (int i = 0; i < threads; i++) {
			logger.info("Running the [" + i +"] thread...");
			executeSingleThread();
		}
	}
	
	public void executeMultiThreads(int threadPools, int threads) {
		ExecutorService exeService = Executors.newFixedThreadPool(threadPools);
		for (int i = 0; i < threads; i++) {
			logger.info("Running the [" + i +"] thread...");
			exeService.execute(run.getRunnable());
		}
		exeService.shutdown();
	}

}
