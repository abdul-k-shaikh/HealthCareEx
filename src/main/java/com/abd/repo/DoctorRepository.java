package com.abd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abd.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
