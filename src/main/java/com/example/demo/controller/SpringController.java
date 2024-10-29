package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.SpringModel;
import com.example.demo.repositoryy.SpringRepo;


@Controller
public class SpringController {
	@Autowired
	SpringRepo repo;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/employee/add")
	public String employeeForm() {
		return "addEmployee";
	}
	
	@PostMapping("/employee/add")
	public String addEmployee(SpringModel data) {
		repo.save(data);
		return "addSuccess";
	}
	
	@GetMapping("/employee/display")
	public String display(Model model) {
		List<SpringModel> empList=(List<SpringModel>)repo.findAll();
		model.addAttribute("employees", empList);
		return "display";
	}
//	public String display() {
//		return "display";
//	}
	
	@GetMapping("/employee/update/{id}")
	public String updateEmployee(@PathVariable("id")Integer id, Model model) {
		SpringModel employee = repo.findById(id).get();
		model.addAttribute("employee", employee);
		return "editEmployee";
	}
	
	@PostMapping("/employee/update")
	public String updateEmployee(SpringModel data) {
		Integer id = data.getId();
		String name = data.getName();
		String email = data.getEmail();
		int phone = data.getPhone();
		String password = data.getPassword();
		
		SpringModel dataDB = repo.findById(id).get();
		dataDB.setName(name);
		dataDB.setEmail(email);
		dataDB.setPhone(phone);
		dataDB.setPassword(password);
		
		repo.save(dataDB);
		
		return "redirect:/employee/display";
	}
	
	@GetMapping("/employee/delete/{id}")
	public String deleteEmployee(@PathVariable("id")Integer id, Model model) {
		repo.deleteById(id);
		return "redirect:/employee/display";
	}
}
