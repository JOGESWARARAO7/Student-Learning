package com.example.mainproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mainproject.Entity.Branches;
import com.example.mainproject.Repo.BranchesRepo;
import com.example.mainproject.serviceDao.BranchesDao;

@Service
public class BrancheService implements BranchesDao{

	@Autowired
	private BranchesRepo branchesRepo;
	
	@Override 
	public Branches findByBranchName(String branch) {
		// TODO Auto-generated method stub
		return branchesRepo.findByBranchname(branch);
	}

}
