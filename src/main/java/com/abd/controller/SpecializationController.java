package com.abd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abd.entity.Specialization;
import com.abd.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService service;
	/**
	 * 1. Show register page
	 * 
	 * */
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}
	
	/**
	 * 2. On submit form save data
	 * 
	 * */
	@PostMapping("/save")
	public String saveForm(
			@ModelAttribute Specialization specialization, Model model
			)
	{    
		Long saveSpecializationId = service.saveSpecialization(specialization);
		String message = "Record ("+saveSpecializationId+") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
		
	}
	
	
	/**
	 * 3. Display all specialization
	 * 
	 * */
	@GetMapping("/all")
	public String viewAll(Model model) {
		List<Specialization>list = service.getAllSpecializations();
		model.addAttribute("list", list);
		return "SpecializationData";
		
		
	}
}
