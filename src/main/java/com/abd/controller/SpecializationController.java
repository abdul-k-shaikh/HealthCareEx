package com.abd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message)
	{
		List<Specialization>list = service.getAllSpecializations();
		model.addAttribute("list", list);
		return "SpecializationData";
	}
	
	/**
	 * 4. Delete by Id
	 * */
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id, RedirectAttributes attributes) 
	{
		service.removeSpecialization(id);
		attributes.addAttribute("message", "Record ("+id+") is removed");
		return "redirect:all";
		
	}
	/**
	 * 5. Fetch data into edit page
	 * */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model)
	{
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("specialization" , spec);
		return "SpecializationEdit"; 
	}
	
}
