package com.abd.controller;

import java.util.List;
import java.util.jar.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abd.entity.Doctor;
import com.abd.exception.DoctorNotFoundException;
import com.abd.service.IDoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;

	// 1. show Register page
	@GetMapping("/register")
	public String showReg(@RequestParam(value = "message", required = false)String message, Model model) 
	{
		model.addAttribute("message", message);
		return "DoctorRegister";
	}

	// 2.save on submit
	@PostMapping("/save")
	public String saveDoctor(@ModelAttribute Doctor doctor, Model model, RedirectAttributes attributes) {
		Long saveDoctorId = service.saveDoctor(doctor);
		String message = "Record (" + saveDoctorId + ") is created";
		model.addAttribute("message", message);
		return "DoctorRegister";
	}

	// 3.display data
	@GetMapping("/all")
	public String display(
			@RequestParam(value = "message", required = false)String message,
			Model model) 
	{
		 List<Doctor> allDoctors = service.getAllDoctors();
		 model.addAttribute("message", allDoctors);
		 model.addAttribute(message, message);
		 return "redirect:register";
		 
	}

	// 4.delete by id
	public String deleteDoctor(@RequestParam long id, RedirectAttributes attributes)
	{
		try {
			service.removeDoctor(id);
			attributes.addAttribute("message", "Doctor ("+id+") is removed");
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute(e.getMessage());
		}
		return "redirect:all";
		
	}

	// 5.show edit

	// 6.email and mobile duplicate validation(AJAX)

	// 7.excel import

}
