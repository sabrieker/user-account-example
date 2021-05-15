package com.sabri.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BaseException extends ResponseStatusException {

	private static final long serialVersionUID = -3713592017680324840L;

	public BaseException(HttpStatus status, String reason) {
		super(status, reason);
	}

}
