package com.simplemad.base.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import com.simplemad.base.domain.AreaCondition;
import com.simplemad.base.domain.CarCondition;
import com.simplemad.base.domain.ChildCondition;
import com.simplemad.base.domain.IntegerScope;
import com.simplemad.base.domain.UserProfile;
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

public class UserProfileConditionUtil {

	public static Criteria carsCriteria(Query<UserProfile> query, List<CarCondition> cars) {
		if(CollectionUtil.isEmpty(cars)) {
			return null;
		}
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		for(int index = 0; index < cars.size(); index++) {
			if(cars.get(index) != null) {
				CriteriaUtil.addCriteria(criteriaList, CarConditionUtil.carCriteria(query, cars.get(index)));
			}
		}
		return CriteriaUtil.toOrCriteria(query, criteriaList);
	}
	
	public static Criteria childrenCriteria(Query<UserProfile> query, List<ChildCondition> children) {
		if(CollectionUtil.isEmpty(children)) {
			return null;
		}
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		for(int index = 0; index < children.size(); index++) {
			if(children.get(index) != null) {
				CriteriaUtil.addCriteria(criteriaList, ChildConditionUtil.childCriteria(query, children.get(index)));
			}
		}
		return CriteriaUtil.toOrCriteria(query, criteriaList);
	}
	
	public static Criteria areaCriteria(Query<UserProfile> query, List<AreaCondition> areas) {
		return criteria(query, areas, "area");
	}
	
	public static Criteria jobCriteria(Query<UserProfile> query, List<Job> jobs) {
		return criteria(query, jobs, "job");
	}
	
	public static Criteria genderCriteria(Query<UserProfile> query, List<Gender> genders) {
		return criteria(query, genders, "gender");
	}
	
	public static Criteria ageCriteria(Query<UserProfile> query,
			List<IntegerScope> ages) {
		if(CollectionUtil.isEmpty(ages)) {
			return null;
		}
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		for(int index = 0; index < ages.size(); index++) {
			IntegerScope scope = ages.get(index);
			if(scope == null) {
				continue;
			}
			Criteria start = ageStartCriteria(query, scope.getMin());
			Criteria end = ageEndCriteria(query, scope.getMax());
			if(start == null && end == null) {
				continue;
			} else if(start == null) {
				CriteriaUtil.addCriteria(criteriaList, end);
			} else if(end == null) {
				CriteriaUtil.addCriteria(criteriaList, start);
			} else {
				CriteriaUtil.addCriteria(criteriaList, query.and(start, end));
			}
		}
		return CriteriaUtil.toOrCriteria(query, criteriaList);
	}

	private static Criteria ageStartCriteria(Query<UserProfile> query, Integer min) {
		if(min == null) {
			return null;
		}
		return query.criteria("birthday").greaterThanOrEq(getDate(min));
	}

	private static Criteria ageEndCriteria(Query<UserProfile> query, Integer max) {
		if(max == null) {
			return null;
		}
		return query.criteria("birthday").lessThanOrEq(getDate(max));
	}
	
	private static Date getDate(int years) {
		long timeMillis = years * 365 * 24 * 60 * 60 * 1000;
		long currentMillis = System.currentTimeMillis();
		return new Date(currentMillis - timeMillis);
	}

	public static Criteria salaryCriteria(Query<UserProfile> query, List<Salary> salaries) {
		return criteria(query, salaries, "salary");
	}
	
	public static Criteria degreeCriteria(Query<UserProfile> query, List<Degree> degrees) {
		return criteria(query, degrees, "degree");
	}
	
	public static Criteria marriageCriteria(Query<UserProfile> query, List<Marriage> marriages) {
		return criteria(query, marriages, "marriage");
	}
	
	public static Criteria bodyCriteria(Query<UserProfile> query, List<BodyType> bodies) {
		return criteria(query, bodies, "body");
	}
	
	public static Criteria familySalaryCriteria(Query<UserProfile> query, List<Salary> familySalaries) {
		return criteria(query, familySalaries, "familySalary");
	}
	
	public static Criteria investmentCriteria(Query<UserProfile> query, List<Investment> investments) {
		return criteria(query, investments, "investments");
	}
	
	public static Criteria spareHobbyCriteria(Query<UserProfile> query, List<SpareHobby> spareHobbies) {
		return criteria(query, spareHobbies, "spareHobbies");
	}
	
	public static Criteria phoneAppHobbyCriteria(Query<UserProfile> query, List<PhoneAppHobby> phoneAppHobbies) {
		return criteria(query, phoneAppHobbies, "phoneAppHobbies");
	}
	
	public static Criteria dietTasteCriteria(Query<UserProfile> query, List<DietTaste> dietTastes) {
		return criteria(query, dietTastes, "tastes");
	}
	
	private static Criteria criteria(Query<UserProfile> query, List<?> dataList, String field) {
		if(CollectionUtil.isEmpty(dataList)) {
			return null;
		}
		return query.criteria(field).hasAnyOf(dataList);
	}
}
