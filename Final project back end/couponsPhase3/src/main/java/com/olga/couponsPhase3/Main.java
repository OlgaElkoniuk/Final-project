package com.olga.couponsPhase3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.entyties.Company;
import com.olga.couponsPhase3.enums.ClientType;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.logic.CompanyController;



@SpringBootApplication

public class Main {
	public static void main(String[] args) throws ApplicationException {

		SpringApplication.run(Main.class, args);

	}
}
