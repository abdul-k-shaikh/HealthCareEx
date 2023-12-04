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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abd.entity.Specialization;
import com.abd.exception.SpecializationNotFoundException;
import com.abd.service.ISpecializationService;
import com.abd.view.SpecializationExcelView;

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
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message", "Record ("+id+") is removed");
			
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage()); 
		}
		
		
		return "redirect:all";
		
	}
	/**
	 * 5. Fetch data into edit page
	 * End user clicks on link, may enter ID manually.
	 * If entered id is present in DB
	 *  >Load row as object
	 *  >Send to edit page
	 *  >Fill the form
	 *  Else
	 *   > Redirect to all (Data page)
	 *   >Show Error message(Not Found)
	 *  
	 * */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes)
	{
		String page = null;
		try {
			Specialization spec = service.getOneSpecialization(id);
			model.addAttribute("specialization" , spec);
			page = "SpecializationEdit";
			
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		    
		}
		
		return page; 
	}
	
	/**
	 * 6. Update form data and redirect to all
	 * */
	
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attributes) 
	
	{   service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Record ("+specialization.getId()+") is updated");
		
		return "redirect:all";
	}
	
	/**
	 * .7 Read code and check with service 
	 *    Return message back to UI
	 * @return 
	 * 
	 * */
	
	@GetMapping("/checkCode")
	//@ResponseBody here also we can write instead at line no. 107
	public @ResponseBody String validateSpecCode(
			@RequestParam String code, @RequestParam Long id) 
	{
		String message = "";
		if(id==0 && service.isSpecCodeExist(code)) { //register check
			message = code + ", Alredy exist";
		}else if(id!=0 && service.isSpecCodeCountExistForEdit(code, id)) { //edit check
			message = code + ", Alredy exist";
		}
		
		return message; //This is not viewName(it is message so we are writing @ResponseBody as return type)
		
	}
	
	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String name)
	{   
		String message = "";
		
		if(service.isSpecNameExist(name)) {
			message = name + ", Alredy exist";
		}
		return message;
		
	}
	
	/**
	 * .8 export data to excel 
	 * */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationExcelView());
		return m;
	}
	
	
	
}
