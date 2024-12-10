package com.example.mainproject.Entity;

public class Otp {

	private String email;
	private int otp;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Otp(String email, int otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "Otp [email=" + email + ", otp=" + otp + "]";
	}
	
	
}
