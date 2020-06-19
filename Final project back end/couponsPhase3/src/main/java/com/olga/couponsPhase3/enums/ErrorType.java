package com.olga.couponsPhase3.enums;

public enum ErrorType {
	GENERAL_ERROR("Technical problems"),
	INVALID_DATE("The date you've entered is invalid"),
	INFO_ALREADY_EXISTS("This info already exists in data base"),
	INFO_DOESNT_EXIST("The info you've entered doesn't exist in data base"),
	VALIDATION_FAILED("You've entered wrong info"),
	LOGIN_FAILED("You failed log in. Try again."),
	NEGATIVE_VALUE("Negative value is unexaptable for this parameter"),
	INVALID_EMAIL("The email you've entered is invalid"),
	FAILED_CREATE(" Create failed"),
	FAILED_DELETE(" Delete failed"),
	FAILED_READ(" Failed get from data base"),
	FAILED_UPDATE(" Update failed"),
	INVALID_PASSWORD(" Password should contain from 8 to 14 characters"),
	INTERNAL_SERVER_ERROR("internal server error"),
	UNAUTHORISED_HTTP("Unauthorised access attempt");
private String errorMessage = null;

private ErrorType(String errorMessage) {
	this.errorMessage = errorMessage;
}
public String getMessage() {
	return errorMessage;
}
	
}
