package com.simplemad.base.domain;

import java.io.Serializable;
import java.util.Date;

public class DateScope implements Serializable {

	private static final long serialVersionUID = -781909170759738511L;
	
	private Date start;
	
	private Date end;
	
	public Date getStart() {
		return this.start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return this.end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public boolean isEmpty() {
		return start == null && end == null;
	}

}
