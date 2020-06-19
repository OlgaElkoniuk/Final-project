package com.olga.couponsPhase3.api;

import java.text.ParseException;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.olga.couponsPhase3.exceptions.ApplicationException;

@RestController
@RequestMapping("/check")
public class CheckSessionApi {
	@GetMapping 
	public void checkSession(@RequestParam("token") int token,HttpServletRequest request) throws ApplicationException, ParseException {
		
	}
}
