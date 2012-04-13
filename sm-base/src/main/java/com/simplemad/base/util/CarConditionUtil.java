package com.simplemad.base.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import com.simplemad.base.domain.CarCondition;
import com.simplemad.base.domain.DateScope;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.domain.enums.CarType;
import com.simplemad.base.domain.enums.Have;

class CarConditionUtil {

	public static Criteria carCriteria(Query<UserProfile> query, CarCondition car) {
		if(car == null) {
			return null;
		}
		if(car.getHave() == null) {
			return null;
		}
		if(car.getHave().equals(Have.HAVE)) {
			return carHaveCriteria(query, car);
		} else {
			return carNotHaveCriteria(query, car);
		}
	}

	private static Criteria carNotHaveCriteria(Query<UserProfile> query,
			CarCondition car) {
		return haveCriteria(query, Have.HAVE_NOT);
	}

	private static Criteria carHaveCriteria(Query<UserProfile> query, CarCondition car) {
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		CriteriaUtil.addCriteria(criteriaList, haveCriteria(query, Have.HAVE));
		CriteriaUtil.addCriteria(criteriaList, buyDateCriteria(query, car.getBuyDates()));
		CriteriaUtil.addCriteria(criteriaList, typeCriteria(query, car.getCarTypes()));
		return CriteriaUtil.toAndCriteria(query, criteriaList);
	}
	
	private static Criteria haveCriteria(Query<UserProfile> query, Have have) {
		return query.criteria("car.have").equal(have);
	}
	
	private static Criteria typeCriteria(Query<UserProfile> query,
			List<CarType> carTypes) {
		if(CollectionUtil.isEmpty(carTypes)) {
			return null;
		}
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		for(int index = 0; index < carTypes.size(); index++) {
			if(carTypes.get(index) != null) {
				CriteriaUtil.addCriteria(criteriaList, query.criteria("car.car").equal(carTypes.get(index)));
			}
		}
		return CriteriaUtil.toOrCriteria(query, criteriaList);
	}

	private static Criteria buyDateCriteria(Query<UserProfile> query,
			List<DateScope> buyDates) {
		if(CollectionUtil.isEmpty(buyDates)) {
			return null;
		}
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		for(int index = 0; index < buyDates.size(); index++) {
			DateScope scope = buyDates.get(index);
			Criteria start = dateScopeStart(query, scope.getStart());
			Criteria end = dateScopeEnd(query, scope.getEnd());
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

	private static Criteria dateScopeStart(Query<UserProfile> query, Date start) {
		if(start == null) {
			return null;
		}
		return query.criteria("car.buyDate").greaterThanOrEq(start);
	}

	private static Criteria dateScopeEnd(Query<UserProfile> query, Date end) {
		if(end == null) {
			return null;
		}
		return query.criteria("car.buyDate").lessThanOrEq(end);
	}
}
