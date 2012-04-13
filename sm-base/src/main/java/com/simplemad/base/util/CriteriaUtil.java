package com.simplemad.base.util;

import java.util.List;

import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;

public class CriteriaUtil {

	public static Criteria[] toArray(List<Criteria> criteriaList) {
		if(CollectionUtil.isEmpty(criteriaList)) {
			return new Criteria[]{};
		}
		Criteria[] criteriaArray = new Criteria[criteriaList.size()];
		for(int index = 0; index < criteriaList.size();index++) {
			criteriaArray[index] = criteriaList.get(index);
		}
		return criteriaArray;
	}
	
	public static void addCriteria(List<Criteria> criteriaList, Criteria criteria) {
		if(criteria != null) {
			criteriaList.add(criteria);
		}
	}
	
	public static Criteria toOrCriteria(Query<?> query, List<Criteria> criteriaList) {
		if(CollectionUtil.isEmpty(criteriaList)) {
			return null;
		}
		if(criteriaList.size() == 1) {
			return criteriaList.get(0);
		} else {
			return query.or(toArray(criteriaList));
		}
	}
	
	public static Criteria toAndCriteria(Query<?> query, List<Criteria> criteriaList) {
		if(CollectionUtil.isEmpty(criteriaList)) {
			return null;
		}
		if(criteriaList.size() == 1) {
			return criteriaList.get(0);
		} else {
			return query.and(toArray(criteriaList));
		}
	}
}
