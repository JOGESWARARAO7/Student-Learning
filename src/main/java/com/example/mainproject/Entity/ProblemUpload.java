package com.example.mainproject.Entity;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class ProblemUpload {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated primary key

	@Column(nullable = false)
    private int userid;
    @Column(nullable = false)
    private String question;
    private String filequestionname;
	private String filequestionType;
    @Lob
    @Column(columnDefinition = "LONGBLOB",nullable = false)
    private byte[] filequestion;
    
    private String fileanswernname;
	private String fileanswerType;
    @Lob
    @Column(columnDefinition = "LONGBLOB",nullable = false)
    private byte[]  fileanswer;
    
    @ManyToOne
    @JoinColumn(name = "branch_name", referencedColumnName = "branchname",nullable = false) 
    private Branches branch;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = Shape.STRING)
	private LocalDate date;

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFilequestionname() {
		return filequestionname;
	}

	public void setFilequestionname(String filequestionname) {
		this.filequestionname = filequestionname;
	}

	public String getFilequestionType() {
		return filequestionType;
	}

	public void setFilequestionType(String filequestionType) {
		this.filequestionType = filequestionType;
	}

	public byte[] getFilequestion() {
		return filequestion;
	}

	public void setFilequestion(byte[] filequestion) {
		this.filequestion = filequestion;
	}

	public String getFileanswernname() {
		return fileanswernname;
	}

	public void setFileanswernname(String fileanswernname) {
		this.fileanswernname = fileanswernname;
	}

	public String getFileanswerType() {
		return fileanswerType;
	}

	public void setFileanswerType(String fileanswerType) {
		this.fileanswerType = fileanswerType;
	}

	public byte[] getFileanswer() {
		return fileanswer;
	}

	public void setFileanswer(byte[] fileanswer) {
		this.fileanswer = fileanswer;
	}

	public Branches getBranch() {
		return branch;
	}

	public void setBranch(Branches branch) {
		this.branch = branch;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ProblemUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProblemUpload(Long id, int userid, String question, String filequestionname, String filequestionType,
			byte[] filequestion, String fileanswernname, String fileanswerType, byte[] fileanswer, Branches branch,
			LocalDate date) {
		super();
		this.id = id;
		this.userid = userid;
		this.question = question;
		this.filequestionname = filequestionname;
		this.filequestionType = filequestionType;
		this.filequestion = filequestion;
		this.fileanswernname = fileanswernname;
		this.fileanswerType = fileanswerType;
		this.fileanswer = fileanswer;
		this.branch = branch;
		this.date = date;
	}

	@Override
	public String toString() {
		return "ProblemUpload [id=" + id + ", userid=" + userid + ", question=" + question + ", filequestionname="
				+ filequestionname + ", filequestionType=" + filequestionType + ", filequestion="
				+ Arrays.toString(filequestion) + ", fileanswernname=" + fileanswernname + ", fileanswerType="
				+ fileanswerType + ", fileanswer=" + Arrays.toString(fileanswer) + ", branch=" + branch + ", date="
				+ date + "]";
	}
	
	

	
    
    
    
	
}
