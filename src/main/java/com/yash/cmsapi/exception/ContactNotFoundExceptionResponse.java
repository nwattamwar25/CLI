package com.yash.cmsapi.exception;

public class ContactNotFoundExceptionResponse {
	private String contactNotFound;

    public ContactNotFoundExceptionResponse(String contactNotFound) {
        this.contactNotFound = contactNotFound;
    }

    public String getContactNotFound() {
        return contactNotFound;
    }

    public void setContactNotFound(String contactNotFound) {
        this.contactNotFound = contactNotFound;
    }
}
