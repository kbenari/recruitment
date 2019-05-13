package com.nexity.recruitment.service;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nexity.recruitment.model.RealEstate;

@Component
public class RealEstateProcessor implements ResourceProcessor<RealEstate>{


	@Override
	public RealEstate process(RealEstate resource) {
		
		resource.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(resource.getIndex()).toUriString()).withSelfRel());
		resource.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand().toUriString()).withRel("list"));
		return resource;
	}

}
