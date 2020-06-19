package com.olga.couponsPhase3.beans;

import com.olga.couponsPhase3.enums.ClientType;

public class UserDataCashe {
	
private long userID;
private ClientType clientType;
private long companyId;

public UserDataCashe(long userID, ClientType clientType, long companyId) {
	super();
	this.userID = userID;
	this.clientType = clientType;
	this.companyId = companyId;
}

public UserDataCashe(long userId, ClientType clientType) {
	this.userID = userId;
	this.clientType = clientType;
}

public UserDataCashe() {
	
}

public long getUserID() {
	return userID;
}

public void setUserID(long userID) {
	this.userID = userID;
}

public ClientType getClientType() {
	return clientType;
}

public void setClientType(ClientType clientType) {
	this.clientType = clientType;
}

public long getCompanyId() {
	return companyId;
}

public void setCompanyId(long companyId) {
	this.companyId = companyId;
}


}
