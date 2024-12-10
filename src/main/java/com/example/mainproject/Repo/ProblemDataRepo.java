package com.example.mainproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.mainproject.Entity.ProblemUserData;

@Repository
public interface ProblemDataRepo extends JpaRepository<ProblemUserData, Integer>{

	ProblemUserData findByEmail(String email);

	ProblemUserData findByUsername(String username);

	

	

}


