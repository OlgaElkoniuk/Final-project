package com.olga.couponsPhase3.beans;

import com.olga.couponsPhase3.enums.ClientType;

public class UserDataClient {
private int token;
private ClientType clientType;
private long  id;
private long  companyId;
private String userName;
public UserDataClient(int token, ClientType clientType, long id, String userName, long  companyId) {
	super();
	this.token = token;
	this.clientType = clientType;
	this.id = id;
	this.userName = userName;
	this.companyId = companyId;
}
public UserDataClient() {
	super();
	// TODO Auto-generated constructor stub
}

public long getCompanyId() {
	return companyId;
}
public void setCompanyId(long companyId) {
	this.companyId = companyId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public int getToken() {
	return token;
}
public void setToken(int token) {
	this.token = token;
}
public ClientType getClientType() {
	return clientType;
}
public void setClientType(ClientType clientType) {
	this.clientType = clientType;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}


}
