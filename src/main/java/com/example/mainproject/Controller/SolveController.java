package com.example.mainproject.Controller;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.mainproject.Entity.Otp;
import com.example.mainproject.Entity.ProblemUpload;
import com.example.mainproject.Entity.SolveGetAllProblem;
import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;
import com.example.mainproject.PojoClass.LoginForm;
import com.example.mainproject.security.AuthResponse;
import com.example.mainproject.security.JwtUtil;
import com.example.mainproject.security.MailAndOtpSend;
import com.example.mainproject.serviceDao.ProblemDataDao;
import com.example.mainproject.serviceDao.SolveDataDao;

import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/solve")
@CrossOrigin(origins = "http://localhost:4200")
public class SolveController {

	@Autowired
	private SolveDataDao solveDataDao;
	
	@Autowired
	private ProblemDataDao problemDataDao;
	
	@Autowired
	private MailAndOtpSend mailandOtpSend;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
    private AuthenticationManager authenticationManager;

	
	// Signup Page
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SolveUserDataTemp solveUserDataTemp){
		
		// this two methods check to the solveuserdata  data base present are not
		SolveUserData sud=solveDataDao.findByEmail(solveUserDataTemp.getEmail());
		SolveUserData sudusername=solveDataDao.findByUsername(solveUserDataTemp.getUsername());
		
		if(sud!=null) {
			return ResponseEntity.badRequest().body("Email alredy present");
		} 
		else if(sudusername!=null) {
			return ResponseEntity.badRequest().body("Username alredy present");
		}
		
		// this method is check to the userDataTemp present email id or not
		SolveUserDataTemp sudt=solveDataDao.findByEmailtemp(solveUserDataTemp.getEmail());
		int otp=Integer.parseInt(mailandOtpSend.generateOtp());
		String subjcetString="Apexify OTP Check";
		String body="Once time password of Apexify /n"+ "don't share any one";
		if(sudt!=null) {
			sudt.setOtp(otp);
			sudt.setPassword(solveUserDataTemp.getPassword());
			sudt.setUsername(solveUserDataTemp.getUsername());
			ResponseEntity<String> mailresponse=mailandOtpSend.sendSimpleEmail(solveUserDataTemp.getEmail(),otp,body,subjcetString);
			if(mailresponse == null ||mailresponse.equals("Invalid Email")) {
				return ResponseEntity.badRequest().body("Incorrect EmailId");
			}
			
			solveDataDao.updateTemp(sudt.getUsername(),sudt.getOtp(),sudt.getPassword(),sudt.getEmail());
	        // Update user data
	        return ResponseEntity.ok(ResponseEntity.ok("User data updated successfully."));
	        		
			
		}
	
		else {
			ResponseEntity<String> mailresponse=mailandOtpSend.sendSimpleEmail(solveUserDataTemp.getEmail(),otp,body,subjcetString);
			if(mailresponse == null ||mailresponse.equals("Invalid Email")) {
				return ResponseEntity.badRequest().body("Incorrect EmailId");
			}
			
	        solveUserDataTemp.setOtp(otp);
	        // Save user data
	        System.out.println("Data save");
	        return ResponseEntity.ok(solveDataDao.saveTemp(solveUserDataTemp));
			
		}
		
		
	}
	
	// Otp Checking
	@PostMapping("/otpChecked")
	private ResponseEntity<?> otpChecked(@RequestBody Otp otp){
		System.out.println("..............."+otp.getEmail());
		SolveUserDataTemp dataTemp=solveDataDao.findByEmailOtp(otp.getEmail());
		System.out.println("..........."+dataTemp.toString());
		if(dataTemp==null) {
			System.out.println(dataTemp.toString());
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		else if(otp.getOtp()!=dataTemp.getOtp()) {
			System.out.println(dataTemp.toString());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("otp not a match");
		}
		SolveUserData data=new SolveUserData();
		data.setEmail(dataTemp.getEmail());
		data.setPassword(dataTemp.getPassword());
		data.setUsername(dataTemp.getUsername());
		solveDataDao.savesolveUserData(data);
		solveDataDao.deleteDataTemp(dataTemp.getUsername());
		 System.out.println("OTP verified successfully for email: " + otp.getEmail());
		    return ResponseEntity.ok(otp);
	}
	
	// Login credentials
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
	    try {
	        ResponseEntity<SolveUserData> userResponse = solveDataDao.findByEmailLogin(loginForm.getEmail(), loginForm.getPassword());
	        if (userResponse.getStatusCode() == HttpStatus.OK) {
	            SolveUserData user = userResponse.getBody();  // Extract the user object
	            
	            if (user != null) {
	                // Successfully authenticated, generate JWT
	                String jwt = jwtUtil.generateToken(user.getEmail(),user.getUsername(),user.getUserid());// Access user email here
	                return ResponseEntity.ok(new AuthResponse(jwt));
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	            }
	            
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	    }
	}

	
	
	@GetMapping("/getallpdfproblem")
	public ResponseEntity<List<SolveGetAllProblem>> getallpdfproblems(@RequestParam("branchSelection") String branchSelection) {
	    List<ProblemUpload> problemUploadbs = problemDataDao.getAllPdfDocuments(branchSelection);

	    if (problemUploadbs == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    List<SolveGetAllProblem> allProblem = new ArrayList<>();
	    for (ProblemUpload problem : problemUploadbs) {
	        SolveGetAllProblem allProblems = new SolveGetAllProblem();

	        allProblems.setUserid(problem.getUserid());
	        allProblems.setQuationid(problem.getId());
	        allProblems.setQuestion(problem.getQuestion());

	        if (problem.getFilequestion() != null) {
	            String encodedFilequestion = Base64.encodeBase64String(problem.getFilequestion());
	            allProblems.setFilequestion(encodedFilequestion); // Set encoded file as String
	        }
	        allProblem.add(allProblems);
	    }

	    return ResponseEntity.ok(allProblem);
	}

	
	
	
}
