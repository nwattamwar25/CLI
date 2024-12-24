package com.yash.cmsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactIdException extends RuntimeException{

	public ContactIdException(String message) {
        super(message);
    }
	
}
