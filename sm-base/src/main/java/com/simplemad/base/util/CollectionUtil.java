package com.simplemad.base.util;

import java.util.Collection;

public class CollectionUtil {

	public static boolean isEmpty(Collection<?> c) {
		if(null == c || c.size() == 0)
			return true;
		return false;
	}
}
