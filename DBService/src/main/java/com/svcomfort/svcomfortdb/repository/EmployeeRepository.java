package com.svcomfort.svcomfortdb.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.svcomfort.svcomfortdb.model.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	@Qualifier("writeTemplate")
	NamedParameterJdbcTemplate writeTemplate;
	
	@Autowired
	@Qualifier("readTemplate")
	NamedParameterJdbcTemplate readTemplate; 
	

	public List<Employee> findDb1List() {
		
		System.out.println(" -------------------- QUERY To db1  ---------------------");
		
		System.out.println(" "+writeTemplate.getJdbcTemplate());

		
		return writeTemplate.queryForList("SELECT id, name FROM Employee", new HashMap());

	}
	
	public List<Employee> findDb2List() {
		System.out.println(" -------------------- QUERY To db2  ---------------------");

		
		//StringBuffer sb = new StringBuffer().append(" SELECT id, name	FROM  \"")
		//		.append("School").append("\" ");
		
		String sb = "SELECT id, name	FROM School";
		
		return readTemplate.queryForList(sb.toString(), new HashMap());

	} 


}
