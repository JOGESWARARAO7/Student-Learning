package com.example.mainproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mainproject.Entity.Branches;

@Repository
public interface BranchesRepo extends JpaRepository<Branches,String>{

	Branches findByBranchname(String branch);

}
