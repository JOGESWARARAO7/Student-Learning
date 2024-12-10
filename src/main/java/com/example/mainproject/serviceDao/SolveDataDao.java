package com.example.mainproject.serviceDao;

import org.springframework.http.ResponseEntity;

import com.example.mainproject.Entity.Otp;
import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;

public interface SolveDataDao{

	SolveUserData findByEmail(String email);

	SolveUserData findByUsername(String username);

	SolveUserDataTemp findByEmailtemp(String email);

	
	SolveUserDataTemp saveTemp(SolveUserDataTemp solveUserDataTemp);

	void updateTemp(String username, int otp, String password, String email);

	SolveUserDataTemp findByEmailOtp(String email);

	void deleteDataTemp(String username);

	void savesolveUserData(SolveUserData data);

	ResponseEntity<SolveUserData> findByEmailLogin(String email, String password);

	

}
