package com.simplemad.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.base.Timer;
import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.util.CollectionUtil;

@Service
public class TimerServiceImpl implements TimerService {

	@Autowired
	private BaseDAO dao;
	
	@Override
	public void create(long start, long end, long interval) {
		Timer timer = new Timer();
		timer.setStart(start);
		timer.setEnd(end);
		timer.setInterval(interval);
		
		Timer oldTimer = find();
		if(oldTimer != null) {
			dao.delete(oldTimer);
		}
		dao.save(timer);
	}

	@Override
	public Timer find() {
		List<Timer> timerList = dao.findAll(Timer.class);
		if(CollectionUtil.isEmpty(timerList)) {
			return null;
		}
		return timerList.get(0);
	}

}
