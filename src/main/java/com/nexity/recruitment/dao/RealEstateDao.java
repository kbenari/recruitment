package com.nexity.recruitment.dao;

import java.util.List;

import com.nexity.recruitment.model.RealEstate;

public interface RealEstateDao {

	List<RealEstate> findAll();

	RealEstate findById(int id);

	RealEstate save(RealEstate realEstate);

	Integer increments();

}