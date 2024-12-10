package com.example.mainproject.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PuserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileslno;
	
	@Column(nullable = false,unique = true)
	private int userid;
	
	@Column(nullable = false,unique = true)
	private String username;
	
	private String fullname;
	
	@Column(nullable = false,unique = true)
	private long mobilenumber;
	
	private String qualification;
	
	private String address;

	public int getProfileslno() {
		return profileslno;
	}

	public void setProfileslno(int profileslno) {
		this.profileslno = profileslno;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PuserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PuserProfile(int profileslno, int userid, String username, String fullname, long mobilenumber,
			String qualification, String address) {
		super();
		this.profileslno = profileslno;
		this.userid = userid;
		this.username = username;
		this.fullname = fullname;
		this.mobilenumber = mobilenumber;
		this.qualification = qualification;
		this.address = address;
	}

	@Override
	public String toString() {
		return "PuserProfile [profileslno=" + profileslno + ", userid=" + userid + ", username=" + username
				+ ", fullname=" + fullname + ", mobilenumber=" + mobilenumber + ", qualification=" + qualification
				+ ", address=" + address + "]";
	}
	
	

	

}
