package com.abd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abd.entity.Doctor;
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
		return repo.findById(id).orElseThrow(()->new DoctorNotFoundException(id+",not exist"));
				

	}

	@Override
	public Doctor getOneDoctor(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDoctor(Doctor doc) {
		// TODO Auto-generated method stub

	}

}
