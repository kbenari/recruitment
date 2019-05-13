/**
 * 
 */
package com.nexity.recruitment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nexity.recruitment.Exception.RealEstateNotFoundException;
import com.nexity.recruitment.model.RealEstate;
import com.nexity.recruitment.service.RealEstateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author kben
 *
 */
@Api("API REST pour lister, afficher et créer des annonces immobilièrs ")
@RestController
@RequestMapping("/annonce")
public class RealEstateController {

	@Autowired
	private RealEstateService realEstateService;
	

    @ApiOperation(value = "Récupère la liste des annonces immobiliéres")
    @RequestMapping( method = RequestMethod.GET ,produces={ "application/hal+json"})
	@CrossOrigin
	public  @ResponseBody ResponseEntity<Object> realEstateList() {
		
		List<RealEstate> realEstates = realEstateService.findAll();
		Resources<RealEstate> resources = new Resources<RealEstate>(realEstates);
		resources.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand().toUriString()).withSelfRel());
		return ResponseEntity.ok(resources);
		
	}

    
	@ApiOperation(value = "Récupère une annonce immobilière grâce à son ID à condition que celui-ci existe en base")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET ,produces={ "application/hal+json"})
	@CrossOrigin
	public  @ResponseBody ResponseEntity<Object>  findRealEstateById(@PathVariable int id) throws RealEstateNotFoundException {
		RealEstate foundRealEstate = realEstateService.findById(id);
		return new ResponseEntity<Object>(foundRealEstate,HttpStatus.OK);
	}

	@ApiOperation(value = "Créer une annonce immobilière")
	@RequestMapping( method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<Object> addRealEstate(@Validated @RequestBody RealEstate realEstate) {
		RealEstate  realEstateAdded = realEstateService.save(realEstate);
		if ( realEstateAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(realEstateAdded.getId()).toUri();
		  
		return ResponseEntity.created(location).body(realEstateAdded);
	}

}


