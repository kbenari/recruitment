package com.nexity.recruitment.service;

import java.util.List;

import com.nexity.recruitment.model.RealEstate;

public interface RealEstateService {

	
	List<RealEstate> findAll();

	RealEstate findById(int id);

	RealEstate save(RealEstate realEstates);
}
