package com.simplemad.base.domain;

import java.util.Date;

import com.simplemad.base.domain.enums.CarType;
import com.simplemad.base.domain.enums.Have;

public class Car extends BaseEntity {

	private static final long serialVersionUID = -8175640569599601739L;

	private Have have;
	
	private CarType car;
	
	private Date buyDate;

	public Have getHave() {
		return have;
	}

	public void setHave(Have have) {
		this.have = have;
	}

	public CarType getCar() {
		return car;
	}

	public void setCar(CarType car) {
		this.car = car;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
}
