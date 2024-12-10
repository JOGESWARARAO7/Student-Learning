package com.example.mainproject.Repo;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mainproject.Entity.SolveUserData;
import com.example.mainproject.Entity.SolveUserDataTemp;

@Repository
public interface SolveDataRepo extends JpaRepository<SolveUserData, Integer>{

	SolveUserData findByEmail(String email);

	SolveUserData findByUsername(String username);

}
