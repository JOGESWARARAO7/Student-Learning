package com.example.mainproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface SolveDataTempRepo extends JpaRepository<SolveUserDataTemp, String>{

	SolveUserDataTemp findByEmail(String email);

	@Transactional 
	@Modifying
	@Query("UPDATE SolveUserDataTemp s SET s.username =?1,s.otp=?2,s.password=?3 WHERE s.email =?4")
	void update(String username, int otp, String password, String email);

	@Modifying
	@Transactional
	@Query("Delete from SolveUserDataTemp s where s.username=?1")
	void deleteByUsername(String username);

	

	
	
}
