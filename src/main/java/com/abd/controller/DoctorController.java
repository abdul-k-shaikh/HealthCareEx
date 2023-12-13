package com.abd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abd.service.IDoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;
	
	//1. show Register page
	@GetMapping("/register")
	public String showReg() {
		return "";
	}
	
	//2.save on submit
	
	//3.display data 
	
	//4.delete by id
	
	//5.show edit
	
	//6.email and mobile duplicate validation(AJAX)
	
	//7.excel import
	
	
	
	

}
