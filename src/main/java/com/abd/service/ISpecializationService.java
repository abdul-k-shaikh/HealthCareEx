package com.abd.service;

import java.util.List;

import com.abd.entity.Specialization;

public interface ISpecializationService {
	
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
}
