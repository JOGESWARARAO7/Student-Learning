package com.example.mainproject.Repo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.mainproject.Entity.ProblemUpload;
import com.example.mainproject.PojoClass.AllUploadProblems;

@Repository
public interface ProblemUploadRepo extends JpaRepository<ProblemUpload,Long>{
	
	@Query("select pu from ProblemUpload pu where pu.userid = ?1 and pu.branch.branchname = ?2")
	List<ProblemUpload> findByUseridAndBranch(int userid, String branchname);

	@Query("select pu from ProblemUpload pu where  pu.branch.branchname = ?1")
	List<ProblemUpload> findByBranch(String branchSelection);

	@Query("select count(*) from ProblemUpload pu where pu.branch.branchname = ?1 and pu.userid = ?2")
	int mechcount(String mechanical,int userid);
	
	@Query("select count(*) from ProblemUpload pu where pu.branch.branchname = ?1 and pu.userid = ?2")
	int Civilcount(String branch,int userid);

	@Query("select count(*) from ProblemUpload pu where pu.branch.branchname = ?1 and pu.userid = ?2")
	int Eeecount(String branch,int userid);

	@Query("select count(*) from ProblemUpload pu where pu.branch.branchname = ?1 and pu.userid = ?2")
	int Csecount(String branch,int userid);
	
//	@Query("select count(userid) from ProblemUpload pu where pu.userid = ?1")
//	int findByCount(int userid);


}
