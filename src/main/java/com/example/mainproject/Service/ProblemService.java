package com.example.mainproject.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mainproject.Entity.Branches;
import com.example.mainproject.Entity.ProblemUpload;
import com.example.mainproject.Entity.ProblemUserData;
import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;
import com.example.mainproject.PojoClass.AllUploadProblems;
import com.example.mainproject.Repo.ProblemDataRepo;
import com.example.mainproject.Repo.ProblemUploadRepo;
import com.example.mainproject.Repo.SolveDataRepo;
import com.example.mainproject.Repo.SolveDataTempRepo;
import com.example.mainproject.serviceDao.ProblemDataDao;

@Service
public class ProblemService implements ProblemDataDao{
	@Autowired
	private SolveDataTempRepo solveDataTempRepo;
	
	@Autowired
	private ProblemUploadRepo problemUploadRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProblemDataRepo problemDataRepo;

	@Override
	public ProblemUserData findByEmail(String email) {
		// TODO Auto-generated method stub
		return problemDataRepo.findByEmail(email);
	}

	@Override
	public ProblemUserData findByUsername(String username) {
		// TODO Auto-generated method stub
		return problemDataRepo.findByUsername(username);
	}

	@Override
	public SolveUserDataTemp findByEmailtemp(String email) {
		// TODO Auto-generated method stub
		return solveDataTempRepo.findByEmail(email);
	}

	@Override
	public void updateTemp(String username, int otp, String password, String email) {
		// TODO Auto-generated method stub
		password=passwordEncoder.encode(password);
		solveDataTempRepo.update(username,otp,password,email);
		
	}

	@Override
	public SolveUserDataTemp saveTemp(SolveUserDataTemp solveUserDataTemp) {
		// TODO Auto-generated method stub
		solveUserDataTemp.setPassword(passwordEncoder.encode(solveUserDataTemp.getPassword()));
		return solveDataTempRepo.save(solveUserDataTemp);
	}

	@Override
	public SolveUserDataTemp findByEmailOtp(String email) {
		SolveUserDataTemp userDataTemp = solveDataTempRepo.findByEmail(email);
		return userDataTemp;
	}

	@Override
	public void savesolveUserData(ProblemUserData data) {
		// TODO Auto-generated method stub
		problemDataRepo.save(data);
		
	}

	@Override
	public void deleteDataTemp(String username) {
		// TODO Auto-generated method stub
		solveDataTempRepo.deleteByUsername(username);
		
	}

	@Override
	public ResponseEntity<ProblemUserData> findByEmailLogin(String email, String password) {
		// TODO Auto-generated method stub
		 ProblemUserData pud=problemDataRepo.findByEmail(email);
		System.out.println(pud);
		if(pud!=null && passwordEncoder.matches(password, pud.getPassword())) {
			return ResponseEntity.ok(pud);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	private boolean isValid(ProblemUpload problemUpload) {
	    
	    return problemUpload.getFilequestion() != null && problemUpload.getFileanswer()!=null;
	          
	}

	
	@Override
	public ResponseEntity<?> problemUploadSave(ProblemUpload problemUpload) {
		// TODO Auto-generated method stub
		
		 if (problemUpload == null || !isValid(problemUpload)) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                             .body("Invalid problem upload data.");
		    }
		 	
		    // Save the object to the repository
		    ProblemUpload uploadedProblem = problemUploadRepo.save(problemUpload);

		    // Check if upload is successful and return appropriate response
		    if (uploadedProblem != null) {
		        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedProblem);
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                             .body("Problem upload failed. Please try again.");
		    }
		
	}

	@Override
	public List<ProblemUpload> findByUseridAndBranch(int userid, String branchname) {
		// TODO Auto-generated method stub
		return problemUploadRepo.findByUseridAndBranch(userid,branchname);
	}

	@Override
	public Optional<ProblemUserData> findByUserid(int userid) {
		// TODO Auto-generated method stub
		return problemDataRepo.findById(userid);
	}

	@Override
	public List<ProblemUpload> getAllPdfDocuments(String branchSelection) {
		// TODO Auto-generated method stub
		return problemUploadRepo.findByBranch(branchSelection);
	}

	@Override
	public int getMechCount(String mechanical,int userid) {
		// TODO Auto-generated method stub
		return problemUploadRepo.mechcount(mechanical,userid);
	}

	@Override
	public int getCivilCount(String branch,int userid) {
		// TODO Auto-generated method stub
		return problemUploadRepo.Civilcount(branch,userid);
	}

	@Override
	public int getEeeCount(String branch,int userid) {
		// TODO Auto-generated method stub
		return problemUploadRepo.Eeecount(branch,userid);
	}

	@Override
	public int getCseCount(String branch,int userid) {
		// TODO Auto-generated method stub
		return problemUploadRepo.Csecount(branch,userid);
	}

	

	

	
	


	

}
