package com.example.mainproject.serviceDao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.mainproject.Entity.Branches;
import com.example.mainproject.Entity.ProblemUpload;
import com.example.mainproject.Entity.ProblemUserData;
import com.example.mainproject.Entity.PuserProfile;
import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;
import com.example.mainproject.PojoClass.AllUploadProblems;

public interface ProblemDataDao {

	ProblemUserData findByEmail(String email);

	ProblemUserData findByUsername(String username);

	SolveUserDataTemp findByEmailtemp(String email);

	void updateTemp(String username, int otp, String password, String email);

	SolveUserDataTemp saveTemp(SolveUserDataTemp solveUserDataTemp);

	SolveUserDataTemp findByEmailOtp(String email);

	void savesolveUserData(ProblemUserData data);

	void deleteDataTemp(String username);

	ResponseEntity<ProblemUserData> findByEmailLogin(String email, String password);

	ResponseEntity<?> problemUploadSave(ProblemUpload problemUpload);

	List<ProblemUpload> findByUseridAndBranch(int userid, String branchname);

	Optional<ProblemUserData> findByUserid(int userid);

	List<ProblemUpload> getAllPdfDocuments(String branchSelection);

	int getMechCount(String mechanical,int userid);

	int getCivilCount(String branch,int userid);

	int getEeeCount(String branch,int userid);

	int getCseCount(String branch,int userid);

	

	

	

	

}
