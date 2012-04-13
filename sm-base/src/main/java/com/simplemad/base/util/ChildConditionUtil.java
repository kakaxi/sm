package com.simplemad.base.util;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import com.simplemad.base.domain.ChildCondition;
import com.simplemad.base.domain.IntegerScope;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.domain.enums.Degree;
import com.simplemad.base.domain.enums.Gender;
import com.simplemad.base.domain.enums.Have;

class ChildConditionUtil {

	public static Criteria childCriteria(Query<UserProfile> query, ChildCondition child) {
		if(child == null) {
			return null;
		}
		if(child.getHave() == null) {
			return null;
		}
		if(child.getHave().equals(Have.HAVE)) {
			return childHaveCriteria(query, child);
		} else {
			return childNotHaveCriteria(query, child);
		}
	}

	private static Criteria childNotHaveCriteria(Query<UserProfile> query,
			ChildCondition child) {
		return haveCriteria(query, Have.HAVE_NOT);
	}
	
	private static Criteria haveCriteria(Query<UserProfile> query,
			Have have) {
		return query.criteria("child.have").equal(have);
	}

	private static Criteria childHaveCriteria(Query<UserProfile> query,
			ChildCondition child) {
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		CriteriaUtil.addCriteria(criteriaList, haveCriteria(query, Have.HAVE));
		CriteriaUtil.addCriteria(criteriaList, genderCriteria(query, child.getGenders()));
		CriteriaUtil.addCriteria(criteriaList, degreeCriteria(query, child.getDegrees()));
		CriteriaUtil.addCriteria(criteriaList, ageCriteria(query, child.getAges()));
		return CriteriaUtil.toAndCriteria(query, criteriaList);
	}

	private static Criteria ageCriteria(Query<UserProfile> query,
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
			Criteria end = agesEndCriteria(query, scope.getMax());
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
		return query.criteria("child.age").greaterThanOrEq(min);
	}

	private static Criteria agesEndCriteria(Query<UserProfile> query, Integer max) {
		if(max == null) {
			return null;
		}
		return query.criteria("child.age").lessThanOrEq(max);
	}

	private static Criteria degreeCriteria(Query<UserProfile> query,
			List<Degree> degrees) {
		if(CollectionUtil.isEmpty(degrees)) {
			return null;
		}
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		for(int index = 0; index < degrees.size(); index++) {
			if(degrees.get(index) != null) {
				CriteriaUtil.addCriteria(criteriaList, query.criteria("child.degree").equal(degrees.get(index)));
			}
		}
		return CriteriaUtil.toOrCriteria(query, criteriaList);
	}

	private static Criteria genderCriteria(Query<UserProfile> query,
			List<Gender> genders) {
		if(CollectionUtil.isEmpty(genders)) {
			return null;
		}
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		for(int index = 0; index < genders.size(); index++) {
			if(genders.get(index) != null) {
				CriteriaUtil.addCriteria(criteriaList, query.criteria("child.gender").equal(genders.get(index)));
			}
		}
		return CriteriaUtil.toOrCriteria(query, criteriaList);
	}
}
