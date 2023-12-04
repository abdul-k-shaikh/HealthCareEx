package com.abd.service;

import java.util.List;

import com.abd.entity.Specialization;

public interface ISpecializationService {
	
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	//#2 for ajax validation
	public boolean isSpecCodeExist(String specCode);
	
	//for name validation 
	public boolean isSpecNameExist(String specName);
	public boolean isSpecCodeCountExistForEdit(String specCode, Long id);
}
