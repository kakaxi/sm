package com.simplemad.base.util;

import com.simplemad.bean.Timer;

public class TimerConverter {

	public static Timer convert(com.simplemad.base.Timer timer) {
		if(timer == null) {
			return null;
		}
		Timer newTimer = new Timer();
		
		newTimer.setStart(timer.getStart());
		newTimer.setEnd(timer.getEnd());
		newTimer.setInterval(timer.getInterval());
		
		return newTimer;
	}
}
