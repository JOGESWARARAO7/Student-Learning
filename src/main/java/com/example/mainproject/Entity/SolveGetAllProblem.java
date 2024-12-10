package com.example.mainproject.Entity;

import jakarta.persistence.Transient;

public class SolveGetAllProblem {
	
	
	private long quationid;
	
	private String username;
	
	private int userid;
	
	private String question;
	
	private String filequestion;

	public long getQuationid() {
		return quationid;
	}

	public void setQuationid(long quationid) {
		this.quationid = quationid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getFilequestion() {
		return filequestion;
	}

	public void setFilequestion(String filequestion) {
		this.filequestion = filequestion;
	}

	
}
