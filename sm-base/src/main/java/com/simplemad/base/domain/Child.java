package com.simplemad.base.domain;

import com.simplemad.base.domain.enums.Degree;
import com.simplemad.base.domain.enums.Gender;
import com.simplemad.base.domain.enums.Have;

public class Child extends BaseEntity {

	private static final long serialVersionUID = -4506232697306567384L;

	private Have have;
	
	private Degree degree;
	
	private Integer age;
	
	private Gender gender;

	public Have getHave() {
		return have;
	}

	public void setHave(Have have) {
		this.have = have;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
