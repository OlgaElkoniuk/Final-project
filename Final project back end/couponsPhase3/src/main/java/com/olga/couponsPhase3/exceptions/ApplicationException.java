package com.olga.couponsPhase3.exceptions;

import com.olga.couponsPhase3.enums.ErrorType;

public class ApplicationException extends Throwable{
private ErrorType errorType;


public ApplicationException(Throwable throwable, ErrorType errorType, String message) {
	super(message, throwable);
	this.errorType = errorType;

	
}
public ApplicationException(ErrorType errorType, String message) {
	super(message);
	this.errorType = errorType;

}
public ApplicationException() {
	super();
}
public ErrorType getErrorType() {
	return errorType;
}


}
