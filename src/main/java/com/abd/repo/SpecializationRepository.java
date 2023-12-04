package com.abd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abd.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
	
	
	//#1 for ajax validation
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode") 
	Integer getSpecCodeCount(String specCode);
	
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND id!=:id")
	Integer getSpecCodeCountForEdit(String specCode, Long id);
	
	
	
	//for name validation
	@Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName")
	Integer getSpecNameCount(String specName);
	
	
	

}
