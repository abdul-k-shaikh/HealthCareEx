package com.abd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abd.entity.Specialization;
import com.abd.exception.SpecializationNotFoundException;
import com.abd.repo.SpecializationRepository;

@Service
public class SpecializationServiceImpl implements ISpecializationService {
	
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));

	}

	/**Q)How you have handled custome exception in ur project
	 * A) below is the example 
	 * 
	 * 
	 * 
	 * */
	@Override
	public Specialization getOneSpecialization(Long id) 
	{
		/*
		 * Optional<Specialization> optional = repo.findById(id);
		 * if(optional.isPresent()) { return optional.get(); } else { throw new
		 * SpecializationNotFoundException(id+ " Not Found"); }
		 */
		
		return repo.findById(id).orElseThrow(
				()->new SpecializationNotFoundException(id+ " Not Found")
				);
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);

	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		/*
		 * Integer specCodeCount = repo.getSpecCodeCount(specCode); 
		 * boolean exist = specCodeCount> 0 ? true : false; 
		 * return exist;
		 */
		return repo.getSpecCodeCount(specCode)>0; 
	}

	@Override
	public boolean isSpecNameExist(String specName) {
		
		return repo.getSpecNameCont(specName)>0;
	}

	
	
	
	
	
	
	
	
	
	
	

}
