package com.svcomfort.hoteldb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.svcomfort.hoteldb.model.Partner;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Integer> {
	
	

}
