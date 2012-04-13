package com.simplemad.base.exception;

import org.springframework.core.ErrorCoded;

public class SimpleException extends RuntimeException implements ErrorCoded {

	private static final long serialVersionUID = 7364657925027153946L;

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
