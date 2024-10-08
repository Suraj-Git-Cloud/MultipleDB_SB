package com.svcomfort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcomfort.svcomfortdb.model.Employee;
import com.svcomfort.svcomfortdb.repository.EmployeeRepository;

@RestController
@RequestMapping
public class EmployeeController {

	@Autowired
	EmployeeRepository repo;

	@GetMapping("/db1")
	public List<Employee> getEmployees() {

		
		System.out.println(" -------------------- Connect To db1 CONTROLLER ---------------------");
		
		return (List<Employee>) repo.findDb1List();
	}

	
	

	@GetMapping("/db2")
	public List<Employee> getSchool() {

		System.out.println(" -------------------- Connect To db2 CONTROLLER ---------------------");
		return (List<Employee>) repo.findDb2List();
	} 
	
	


}
