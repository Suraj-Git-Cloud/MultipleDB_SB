package com.svcomfort.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcomfort.hoteldb.model.Partner;
import com.svcomfort.svcomfortdb.model.Employee;
import com.svcomfort.svcomfortdb.repository.EmployeeRepository;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	

@Autowired
EmployeeRepository repo;
	
@GetMapping("/all")
public List<Employee> getEmployees() {	
	
	return (List<Employee>) repo.findAll();
}

@PostMapping("/addEmployee")
public ResponseEntity addEmployee(@RequestBody Employee employee) {
	
	String msg = "";
	 Employee emp = repo.save(employee);
	
	 if(emp!=null)
	 	 msg ="Success";
	 else
		 msg ="Insert Fail";	 
	 
	 return new ResponseEntity<String>(msg,HttpStatus.OK);
	 
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable ("id") int id) {
	
	String msg ="Success";
	
	repo.deleteById(id);
	
	return new ResponseEntity<String>(msg,HttpStatus.OK);
	
}
@GetMapping("/{id}")
public String findEmployeeById(@PathVariable ("id") int id) {
	
Optional<Employee> emp = repo.findById(id);	
	
	if(emp.isPresent()) 
		return emp.get().getName();	
	else
		return " Not Existing ID";
		
	
}




}
