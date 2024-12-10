package com.example.mainproject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SolveUserDataTemp {

	
	@Id
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private int otp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public SolveUserDataTemp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SolveUserDataTemp(String username, String email, String password, int otp) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "SolveUserDataTemp [username=" + username + ", email=" + email + ", password=" + password + ", otp="
				+ otp + "]";
	}

	
	
}
