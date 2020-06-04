 package com.vp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class JobNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;

public JobNotFoundException(String message)
{
	super(message);
}
}
