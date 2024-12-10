package com.example.mainproject.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.mainproject.Entity.PuserProfile;

@Repository
public interface PuserProfileRepo extends JpaRepository<PuserProfile,Integer>{

	Optional<PuserProfile> findByUserid(int userid);

}
