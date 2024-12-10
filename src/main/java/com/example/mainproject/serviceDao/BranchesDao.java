package com.example.mainproject.serviceDao;

import com.example.mainproject.Entity.Branches;

public interface BranchesDao {

	Branches findByBranchName(String branch);

}
