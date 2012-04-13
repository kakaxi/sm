package com.simplemad.base;

import com.google.code.morphia.annotations.Entity;
import com.simplemad.base.domain.BaseEntity;

@Entity
public class Timer extends BaseEntity {

	private static final long serialVersionUID = 2216047546207405815L;

	private long start;

	private long end;

	private long interval;

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
}
