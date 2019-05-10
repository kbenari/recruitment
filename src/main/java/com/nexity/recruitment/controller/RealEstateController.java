/**
 * 
 */
package com.nexity.recruitment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nexity.recruitment.Exception.RealEstateNotFoundException;
import com.nexity.recruitment.model.RealEstate;
import com.nexity.recruitment.service.RealEstateService;

/**
 * @author kben
 *
 */
@RestController
public class RealEstateController {

	@Autowired
	private RealEstateService realEstateService;

	@RequestMapping(value = "/Annonces", method = RequestMethod.GET)
	public List<RealEstate> realEstateList() {
		return realEstateService.findAll();
	}

	@RequestMapping(value = "/Annonces/{id}", method = RequestMethod.GET)
	public RealEstate findRealEstateById(@PathVariable int id) throws RealEstateNotFoundException {
		return realEstateService.findById(id);
	}

	@RequestMapping(value = "/Annonces", method = RequestMethod.POST)
	public ResponseEntity<Object> addRealEstate(@RequestBody RealEstate realEstate) {
		RealEstate  realEstateAdded = realEstateService.save(realEstate);
		if ( realEstateAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(realEstateAdded.getId()).toUri();
		

		  
		return ResponseEntity.created(location).body(realEstateAdded);
	}

}
