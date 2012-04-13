package com.simplemad.base.domain;

import java.io.Serializable;
import java.util.List;

import com.simplemad.base.domain.enums.Degree;
import com.simplemad.base.domain.enums.Gender;
import com.simplemad.base.domain.enums.Have;

public class ChildCondition implements Serializable {

	private static final long serialVersionUID = 9087960207230955374L;

	private Have have;
	
	private List<Gender> genders;
	
	private List<Degree> degrees;
	
	private List<IntegerScope> ages;

	public Have getHave() {
		return have;
	}

	public void setHave(Have have) {
		this.have = have;
	}

	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public List<IntegerScope> getAges() {
		return ages;
	}

	public void setAges(List<IntegerScope> ages) {
		this.ages = ages;
	}
}
