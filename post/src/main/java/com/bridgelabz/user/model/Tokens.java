package com.bridgelabz.user.model;

import java.util.List;

public class Tokens {
	private String tokenstring;
	private String usernaame;
	private List<Tokens> tokenlist;

	public Tokens() {
		super();
	}

	public Tokens(String tokenstring, String usernaame) {
		super();
		this.tokenstring = tokenstring;
		this.usernaame = usernaame;
	}

	public String getTokenstring() {
		return tokenstring;
	}

	public void setTokenstring(String tokenstring) {
		this.tokenstring = tokenstring;
	}

	public String getUsernaame() {
		return usernaame;
	}

	public void setUsernaame(String usernaame) {
		this.usernaame = usernaame;
	}

	public List<Tokens> getTokenlist() {
		return tokenlist;
	}

	public void setTokenlist(List<Tokens> tokenlist) {
		this.tokenlist = tokenlist;
	}

}
