package com.example.mainproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mainproject.Entity.Otp;
import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;
import com.example.mainproject.Repo.SolveDataRepo;
import com.example.mainproject.Repo.SolveDataTempRepo;
import com.example.mainproject.serviceDao.SolveDataDao;



@Service
public class SolveService implements SolveDataDao{

	@Autowired
	private SolveDataTempRepo solveDataTempRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SolveDataRepo solveDataRepo;

	@Override
	public SolveUserData findByEmail(String email) {
		// TODO Auto-generated method stub
		return solveDataRepo.findByEmail(email);
	}

	@Override
	public SolveUserData findByUsername(String username) {
		// TODO Auto-generated method stub
		return solveDataRepo.findByUsername(username);
	}

	@Override
	public SolveUserDataTemp findByEmailtemp(String email) {
		// TODO Auto-generated method stub
		return solveDataTempRepo.findByEmail(email);
	}


	@Override
	public SolveUserDataTemp saveTemp(SolveUserDataTemp solveUserDataTemp) {
		// TODO Auto-generated method stub
		solveUserDataTemp.setPassword(passwordEncoder.encode(solveUserDataTemp.getPassword()));
		return solveDataTempRepo.save(solveUserDataTemp);
	}

	

	@Override
	public void updateTemp(String username, int otp, String password, String email) {
		// TODO Auto-generated method stub
		password=passwordEncoder.encode(password);
		solveDataTempRepo.update(username,otp,password,email);
	}

	@Override
	public SolveUserDataTemp findByEmailOtp(String email) {
		// TODO Auto-generated method stub
		SolveUserDataTemp userDataTemp = solveDataTempRepo.findByEmail(email);
		return userDataTemp;
	}

	@Override
	public void deleteDataTemp(String username) {
		// TODO Auto-generated method stub
		solveDataTempRepo.deleteByUsername(username);
	}

	@Override
	public void savesolveUserData(SolveUserData data) {
		// TODO Auto-generated method stub
		solveDataRepo.save(data);
		System.out.println("Save");
	}

	@Override
	public ResponseEntity<SolveUserData> findByEmailLogin(String email, String password) {
		// TODO Auto-generated method stub
		SolveUserData sud=solveDataRepo.findByEmail(email);
		System.out.println(sud);
		if(sud!=null && passwordEncoder.matches(password, sud.getPassword())) {
			System.out.println("hi");
			return ResponseEntity.ok(sud);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
