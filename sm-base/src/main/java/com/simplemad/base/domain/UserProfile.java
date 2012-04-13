package com.simplemad.base.domain;

import java.util.Date;
import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.simplemad.base.domain.enums.BodyType;
import com.simplemad.base.domain.enums.Degree;
import com.simplemad.base.domain.enums.DietTaste;
import com.simplemad.base.domain.enums.Gender;
import com.simplemad.base.domain.enums.Investment;
import com.simplemad.base.domain.enums.Job;
import com.simplemad.base.domain.enums.Marriage;
import com.simplemad.base.domain.enums.PhoneAppHobby;
import com.simplemad.base.domain.enums.Salary;
import com.simplemad.base.domain.enums.SpareHobby;

@Entity
public class UserProfile extends BaseEntity {

	private static final long serialVersionUID = -5950700717088244011L;

	@Reference(lazy = true)
	private User user;

	private long money;
	
	private long exchangeMoney;
	
	private Date registerDate;

	/** first page */
	private String userName;

	private Gender gender;

	private Date birthday;
	
	@Reference(lazy=true)
	private Area area;
	
	private String address;
	
	private Salary salary;
	
	private Job job;
	
	/** second page */
	
	private Marriage marriage;
	
	private Child child;
	
	private BodyType body;
	
	private Degree degree;
	
	private Salary familySalary;
	
	private Car car;
	
	/** third page */
	
	private List<Investment> investments;
	
	private List<SpareHobby> spareHobbies;
	
	private List<PhoneAppHobby> phoneAppHobbies;
	
	private List<DietTaste> tastes;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Marriage getMarriage() {
		return marriage;
	}

	public void setMarriage(Marriage marriage) {
		this.marriage = marriage;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public BodyType getBody() {
		return body;
	}

	public void setBody(BodyType body) {
		this.body = body;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Salary getFamilySalary() {
		return familySalary;
	}

	public void setFamilySalary(Salary familySalary) {
		this.familySalary = familySalary;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(List<Investment> investments) {
		this.investments = investments;
	}

	public List<SpareHobby> getSpareHobbies() {
		return spareHobbies;
	}

	public void setSpareHobbies(List<SpareHobby> spareHobbies) {
		this.spareHobbies = spareHobbies;
	}

	public List<PhoneAppHobby> getPhoneAppHobbies() {
		return phoneAppHobbies;
	}

	public void setPhoneAppHobbies(List<PhoneAppHobby> phoneAppHobbies) {
		this.phoneAppHobbies = phoneAppHobbies;
	}

	public List<DietTaste> getTastes() {
		return tastes;
	}

	public void setTastes(List<DietTaste> tastes) {
		this.tastes = tastes;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public long getExchangeMoney() {
		return exchangeMoney;
	}

	public void setExchangeMoney(long exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}
}
