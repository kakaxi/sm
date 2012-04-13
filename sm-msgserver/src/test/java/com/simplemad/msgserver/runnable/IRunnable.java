package com.simplemad.msgserver.runnable;

import com.simplemad.bean.Message;

public interface IRunnable {
	Message createMessage();
	
	Runnable getRunnable();
}
