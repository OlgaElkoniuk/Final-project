package com.olga.couponsPhase3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {



	public static boolean validateEmailAddress(String emailAddress) {
	
	Pattern regexPattern = null;
	Matcher regMatcher= null;
		regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		regMatcher = regexPattern.matcher(emailAddress);
		if (regMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
