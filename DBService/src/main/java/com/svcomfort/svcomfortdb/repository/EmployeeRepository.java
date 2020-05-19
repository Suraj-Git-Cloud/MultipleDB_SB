package com.svcomfort.svcomfortdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.svcomfort.svcomfortdb.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>   {
	

}
