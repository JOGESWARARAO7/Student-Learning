package com.example.mainproject.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Branches {

	@Id
	String branchname;
	
	@JsonBackReference
	@OneToMany(mappedBy = "branch")
    private List<ProblemUpload> problemupload;

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public List<ProblemUpload> getProblemupload() {
		return problemupload;
	}

	public void setProblemupload(List<ProblemUpload> problemupload) {
		this.problemupload = problemupload;
	}

	public Branches() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Branches(String branchname, List<ProblemUpload> problemupload) {
		super();
		this.branchname = branchname;
		this.problemupload = problemupload;
	}

	@Override
	public String toString() {
		return "Branches [branchname=" + branchname + ", problemupload=" + problemupload + "]";
	}
	
	
}
