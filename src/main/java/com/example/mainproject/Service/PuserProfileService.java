package com.example.mainproject.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.mainproject.Entity.PuserProfile;
import com.example.mainproject.Repo.PuserProfileRepo;
import com.example.mainproject.serviceDao.PuserProfileDao;

@Service
public class PuserProfileService implements PuserProfileDao{
	
	@Autowired
	private PuserProfileRepo profileRepo;

	@Override
	public PuserProfile save(PuserProfile puserProfile) {
		// TODO Auto-generated method stub
		return profileRepo.save(puserProfile);
		
	}

	 @Override
	    public ResponseEntity<PuserProfile> findByUserid(int userid) {
	        Optional<PuserProfile> profile = profileRepo.findByUserid(userid);  // Returns Optional
	        if (profile.isPresent()) {
	            return ResponseEntity.ok(profile.get());  // Return 200 OK with profile
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 Not Found
	        }
	    }

}
