package com.abd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abd.entity.Doctor;
import com.abd.exception.DoctorNotFoundException;
import com.abd.repo.DoctorRepository;


@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository repo;

	@Override
	public Long saveDoctor(Doctor doc) {
		return repo.save(doc).getId();
		 
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));
				

	}

	@Override
	public Doctor getOneDoctor(Long id) {
		return repo.findById(id).orElseThrow(()->new DoctorNotFoundException(id+",not exist"));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId()))
			repo.save(doc);
		else throw new DoctorNotFoundException(doc.getId()+",not exist");

	}

}
