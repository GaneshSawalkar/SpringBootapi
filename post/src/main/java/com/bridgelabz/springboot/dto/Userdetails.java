package com.bridgelabz.springboot.dto;

public class Userdetails {

	private int id;

	private String userName;
	private String email;
	private String address;
	private long mobileNo;
	private String joined;
	private String modified;
	private int verify;

	public Userdetails() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getJoin() {
		return joined;
	}

	public void setJoin(String string) {
		this.joined = string;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", address=" + address + ", mobileNo="
				+ mobileNo + ",verify" + verify + "]";
	}

}
