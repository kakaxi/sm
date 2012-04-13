package com.simplemad.base.service;

import com.simplemad.base.Timer;

public interface TimerService {

	public void create(long start, long end, long interval);
	
	public Timer find();
}
