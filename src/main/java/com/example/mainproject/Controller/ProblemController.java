package com.example.mainproject.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.ParserInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mainproject.Entity.Branches;
import com.example.mainproject.Entity.Otp;
import com.example.mainproject.Entity.ProblemUpload;
import com.example.mainproject.Entity.ProblemUserData;
import com.example.mainproject.Entity.PuserProfile;
import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;
import com.example.mainproject.PojoClass.AllUploadProblems;
import com.example.mainproject.PojoClass.LoginForm;
import com.example.mainproject.security.AuthResponse;
import com.example.mainproject.security.JwtUtil;
import com.example.mainproject.security.MailAndOtpSend;
import com.example.mainproject.serviceDao.BranchesDao;
import com.example.mainproject.serviceDao.ProblemDataDao;
import com.example.mainproject.serviceDao.PuserProfileDao;
import com.example.mainproject.serviceDao.SolveDataDao;

@RestController
@RequestMapping("/problem")
@CrossOrigin(origins = "http://localhost:4200")
public class ProblemController {
	
	@Autowired
	private ProblemDataDao problemDataDao;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private BranchesDao branchesDao;
	
	@Autowired
	private PuserProfileDao puserProfileDao;
	
	@Autowired
	private MailAndOtpSend mailandOtpSend;
	

	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	// Signup Page problem user
		@PostMapping("/problemsignup")
		public ResponseEntity<?> signup(@RequestBody SolveUserDataTemp solveUserDataTemp){
			
			// this two methods check to the problemuserdata  data base present are not
			ProblemUserData pud=problemDataDao.findByEmail(solveUserDataTemp.getEmail());
			ProblemUserData pobusername=problemDataDao.findByUsername(solveUserDataTemp.getUsername());
			
			if(pud!=null) {
				return ResponseEntity.badRequest().body("Email alredy present");
			} 
			else if(pobusername!=null) {
				return ResponseEntity.badRequest().body("Username alredy present");
			}
			
			// this method is check to the userDataTemp present email id or not
			SolveUserDataTemp sudt=problemDataDao.findByEmailtemp(solveUserDataTemp.getEmail());
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
				
				problemDataDao.updateTemp(sudt.getUsername(),sudt.getOtp(),sudt.getPassword(),sudt.getEmail());
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
		        return ResponseEntity.ok(problemDataDao.saveTemp(solveUserDataTemp));
				
			}
			
			
		}
		
		// Otp Checking
		@PostMapping("/otpChecked")
		private ResponseEntity<?> otpChecked(@RequestBody Otp otp){
			System.out.println("..............."+otp.getEmail());
			SolveUserDataTemp dataTemp=problemDataDao.findByEmailOtp(otp.getEmail());
			System.out.println("..........."+dataTemp.toString());
			if(dataTemp==null) {
				System.out.println(dataTemp.toString());
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}
			else if(otp.getOtp()!=dataTemp.getOtp()) {
				System.out.println(dataTemp.toString());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("otp not a match");
			}
			ProblemUserData data=new ProblemUserData();
			data.setEmail(dataTemp.getEmail());
			data.setPassword(dataTemp.getPassword());
			data.setUsername(dataTemp.getUsername());
			problemDataDao.savesolveUserData(data);
			problemDataDao.deleteDataTemp(dataTemp.getUsername());
			 System.out.println("OTP verified successfully for email: " + otp.getEmail());
			    return ResponseEntity.ok(otp);
		}
		
		// Login credentials
		@PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
		    try {
		        ResponseEntity<ProblemUserData> user = problemDataDao.findByEmailLogin(loginForm.getEmail(), loginForm.getPassword());
		        
		        if (user.getStatusCode() == HttpStatus.OK) {
		            // Successfully authenticated
		        	ProblemUserData problemUserData=user.getBody();
		        	if (user != null) {
		                // Successfully authenticated, generate JWT
		                String jwt = jwtUtil.generateToken(problemUserData.getEmail(),problemUserData.getUsername(),problemUserData.getUserid());// Access user email here
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

		
		
		// Problem Upload pdf method
		@PostMapping("/pupupload")
		public ResponseEntity<?> uploadproblem(
				 @RequestParam("branch") String branch,
				    @RequestParam("question") String question,
				    @RequestParam("userId") int userId,
				    @RequestParam(value = "fileQuestion", required = false) MultipartFile fileQuestion,
				    @RequestParam(value = "fileAnswer", required = false) MultipartFile fileAnswer
				){
			// Convert MultipartFile to byte array and set properties
		    ProblemUpload problemUpload = new ProblemUpload();
		    problemUpload.setBranch(branchesDao.findByBranchName(branch));
		    problemUpload.setUserid(userId);
		    problemUpload.setQuestion(question);
		    problemUpload.setDate(LocalDate.now());

		    try {
		        if (fileQuestion != null) {
		            problemUpload.setFilequestion(fileQuestion.getBytes());
		            problemUpload.setFilequestionname(fileQuestion.getOriginalFilename());
		            problemUpload.setFilequestionType(fileQuestion.getContentType());
		        }
		        if (fileAnswer != null) {
		            problemUpload.setFileanswer(fileAnswer.getBytes());
		            problemUpload.setFileanswernname(fileAnswer.getOriginalFilename());
		            problemUpload.setFileanswerType(fileAnswer.getContentType());
		        }
		    } catch (IOException e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file data");
		    }

		    return problemDataDao.problemUploadSave(problemUpload);
		}
		
		// Get all problems based on branch
		
		@GetMapping("/getuploadproblem")
		public ResponseEntity<?> getuploadproblem(@RequestParam("branchSelection") String branchname,@RequestParam("userid") int userid){
		
			List<AllUploadProblems> allUploadProblems=new ArrayList<>();
			List<ProblemUpload> Problems=problemDataDao.findByUseridAndBranch(userid,branchname);
			for (ProblemUpload problemUpload : Problems) {
				AllUploadProblems uploadProblem = new AllUploadProblems();
		        uploadProblem.setQuestion(problemUpload.getQuestion());  // Assuming `setQuestion` is a method in `AllUploadProblems`
		        allUploadProblems.add(uploadProblem);
			}
			return ResponseEntity.ok(allUploadProblems);
			
		}
		
		@PostMapping("/uploadprofile")
		public ResponseEntity<PuserProfile> uploadprofile(@RequestBody PuserProfile puserProfile) {
			 System.out.println("Received profile data: " + puserProfile.getUsername()); // Debug log
		    if (puserProfile.getUserid()== 0 || puserProfile.getUsername()== null) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		    }
		    
		    Optional<ProblemUserData> user = problemDataDao.findByUserid(puserProfile.getUserid());
		    
		    if (user != null) {
		        
		        PuserProfile profile = puserProfileDao.save(puserProfile);
		        System.out.println(profile);
		        
		        return ResponseEntity.ok(profile);
		    } else {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		    }
		}
		
		// get profile inbulit
		@GetMapping("/getinbultpuserprofile")
		public ResponseEntity<PuserProfile> getinbultpuserprofile(@RequestParam("userid") int userid){
			
			return puserProfileDao.findByUserid(userid);
			
		}
		
		@GetMapping("/getmech")
		public int getmech(@RequestParam("branch") String branch,@RequestParam("userid") int userid){
		
			return problemDataDao.getMechCount(branch,userid);
		}
		@GetMapping("/getcivil")
		public int getcivil(@RequestParam("branch") String branch,@RequestParam("userid") int userid){
	
			return problemDataDao.getCivilCount(branch,userid);
		}
		
		@GetMapping("/geteee")
		public int geteee(@RequestParam("branch") String branch,@RequestParam("userid") int userid){
			
			return problemDataDao.getEeeCount(branch,userid);
		}
		@GetMapping("/getcse")
		public int getcse(@RequestParam("branch") String branch,@RequestParam("userid") int userid){
		
			return problemDataDao.getCseCount(branch,userid);
		}

		


}
