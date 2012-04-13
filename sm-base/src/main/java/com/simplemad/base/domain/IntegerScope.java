package com.simplemad.base.domain;

import java.io.Serializable;

public class IntegerScope implements Serializable {

	private static final long serialVersionUID = -1392838527846945136L;

	private Integer min;
	
	private Integer max;
	
	public Integer getMin() {
		return min;
	}
	
	public void setMin(Integer min) {
		this.min = min;
	}
	
	public Integer getMax() {
		return max;
	}
	
	public void setMax(Integer max) {
		this.max = max;
	}
	
	public boolean isEmpty() {
		return min == null && max == null;
	}
}
