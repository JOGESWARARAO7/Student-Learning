package com.example.mainproject.serviceDao;

import org.springframework.http.ResponseEntity;

import com.example.mainproject.Entity.PuserProfile;

public interface PuserProfileDao {

	PuserProfile save(PuserProfile puserProfile);

	ResponseEntity<PuserProfile> findByUserid(int userid);

}
