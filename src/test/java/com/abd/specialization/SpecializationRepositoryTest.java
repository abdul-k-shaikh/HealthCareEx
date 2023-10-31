package com.abd.specialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.abd.repo.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SpecializationRepositoryTest {
	
	@Autowired
	private SpecializationRepository repo;
	
    /**
     * 1.Test save operation
     * 
     * */
	
	
	/**
	 * 2. Test display all operation
	 * 
	 * 
	 * */
}
