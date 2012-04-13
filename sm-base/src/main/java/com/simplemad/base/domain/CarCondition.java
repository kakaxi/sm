package com.simplemad.base.domain;

import java.io.Serializable;
import java.util.List;

import com.simplemad.base.domain.enums.CarType;
import com.simplemad.base.domain.enums.Have;

public class CarCondition implements Serializable {

	private static final long serialVersionUID = -1640920141160267578L;

	private Have have;
	
	private List<CarType> carTypes;
	
	private List<DateScope> buyDates;

	public Have getHave() {
		return have;
	}

	public void setHave(Have have) {
		this.have = have;
	}

	public List<CarType> getCarTypes() {
		return carTypes;
	}

	public void setCarTypes(List<CarType> carTypes) {
		this.carTypes = carTypes;
	}

	public List<DateScope> getBuyDates() {
		return buyDates;
	}

	public void setBuyDates(List<DateScope> buyDates) {
		this.buyDates = buyDates;
	}
}
