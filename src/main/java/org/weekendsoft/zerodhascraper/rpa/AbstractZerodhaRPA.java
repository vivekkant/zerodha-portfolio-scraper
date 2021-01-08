package org.weekendsoft.zerodhascraper.rpa;

public class AbstractZerodhaRPA {
	protected String username;
	protected String password;
	protected String pin;
	
	public AbstractZerodhaRPA(String username, String password, String pin) {
		this.username = username;
		this.password = password;
		this.pin = pin;
	}

}
