package com.svcomfort.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcomfort.hoteldb.model.Partner;
import com.svcomfort.hoteldb.repository.PartnerRepository;
import com.svcomfort.svcomfortdb.model.Employee;
import com.svcomfort.svcomfortdb.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/svcomforts/db-service/partner")
public class PartnerController {
	

@Autowired
PartnerRepository repo;
	
@GetMapping("/")
public List<Partner> getPartners() {
	
	
	
	return (List<Partner>) repo.findAll();
}

@PostMapping("/addPartner")
public ResponseEntity addPartner(@RequestBody Partner partner) {
	
	String msg = "";
	Partner emp = repo.save(partner);
	
	 if(emp!=null)
	 	 msg ="Success";
	 else
		 msg ="Insert Fail";	 
	 
	 return new ResponseEntity<String>(msg,HttpStatus.OK);
	 
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deletePartner(@PathVariable ("id") int id) {
	
	String msg ="Success";
	
	repo.deleteById(id);
	
	return new ResponseEntity<String>(msg,HttpStatus.OK);
	
}
@GetMapping("/{id}")
public String findPartnerById(@PathVariable ("id") int id) {
	
	Optional<Partner> emp = repo.findById(id);	
	
	if(emp.isPresent()) 
		return emp.get().getName();	
	else
		return " Not Existing ID";
		
	
}

}
