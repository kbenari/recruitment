package com.nexity.recruitment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexity.recruitment.Exception.RealEstateAlreadyExistingException;
import com.nexity.recruitment.Exception.RealEstateNotFoundException;
import com.nexity.recruitment.dao.RealEstateDaoImpl;
import com.nexity.recruitment.model.RealEstate;
import com.nexity.recruitment.service.RealEstateService;


@RunWith(SpringRunner.class)
@WebMvcTest(RealEstateController.class)
public class RealEstateControllerTest {
	private static final String FIND_BY_ID_1 = "{\"index\":1,\"surface\":45.0,\"titre\":\"appartement\",\"descriptif\":\"exemple d'appartement\","
			+ "\"adresse\":\"20 rue GateVigne 78200 Mantes la jolie\",\"prix\":145000}";
	

	private static String FIND_ALL_RESULT="{\"_embedded\":{\"realEstateList\":[{\"index\":1,\"surface\":45.0,\"titre\":\"appartement\",\"descriptif\":"
			+ "\"exemple d'appartement\",\"adresse\":\"20 rue GateVigne 78200 Mantes la jolie\",\"prix\":145000},{\"index\":2,\"surface\":1235.0,\"titre"
			+ "\":\"immeuble\",\"descriptif\":\"exemple d'immeuble\",\"adresse\":\"19 Rue de Vienne, 75801 Paris\",\"prix\":12000000}]},\"_links\":{\"self\""
			+ ":{\"href\":\"http://localhost/annonce\"}}}";

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RealEstateService  realEstateService ;
	
	
	@Test
	public void findAll() throws Exception {
		
		Mockito.when(realEstateService.findAll()).thenReturn(new RealEstateDaoImpl().findAll());
		
		this.mockMvc.perform(get("/annonce")).andExpect(status().isOk())
				.andExpect(content().json(FIND_ALL_RESULT));
	}
	
	@Test
	public void findByIdForFound() throws Exception {
		
		Mockito.when(realEstateService.findById(1)).thenReturn(new RealEstateDaoImpl().findById(1));
		
		this.mockMvc.perform(get("/annonce/1")).andExpect(status().isOk())
				.andExpect(content().json(FIND_BY_ID_1));
	}
	
	@Test
	public void noFoundeFindByIdForNoFound() throws Exception {
		
		Mockito.when(realEstateService.findById(1)).thenThrow(new  RealEstateNotFoundException("Annonce immobiliere avec id 1 inexistante"));
		
		this.mockMvc.perform(get("/annonce/1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void saveForNonExisting() throws Exception {
		
		RealEstate realEstateToSave =  new RealEstate(null,"hutte rudementaire", "exemple ","20 rue GateVigne 78200 Mantes la jolie", 45, new BigDecimal (145000)) ;
		
		Mockito.when(realEstateService.save(realEstateToSave )).thenReturn(realEstateToSave);
		
		this.mockMvc.perform(post("/annonce").contentType(MediaType.APPLICATION_JSON).content( new ObjectMapper().writeValueAsString(realEstateToSave)))
		.andExpect(status().isCreated());
	}

	@Test
	public void saveForExisting() throws Exception {
		
		RealEstate realEstateToSave =  new RealEstate(1,"hutte rudementaire", "exemple ","20 rue GateVigne 78200 Mantes la jolie", 45, new BigDecimal (145000)) ;
		
		Mockito.when(realEstateService.save(realEstateToSave )).thenThrow(new RealEstateAlreadyExistingException("notfound"));
		
		this.mockMvc.perform(post("/annonce").contentType(MediaType.APPLICATION_JSON).content( new ObjectMapper().writeValueAsString(realEstateToSave)))
		.andExpect(status().isConflict());
	}
	
	
	@Test
	public void saveForNoValidateTitle() throws Exception {
		
		RealEstate realEstateToSave =  new RealEstate(1,"", "exemple ","20 rue GateVigne 78200 Mantes la jolie", 45, new BigDecimal (145000)) ;
		
		Mockito.when(realEstateService.save(realEstateToSave )).thenThrow(new RealEstateAlreadyExistingException("notfound"));
		
		this.mockMvc.perform(post("/annonce").contentType(MediaType.APPLICATION_JSON).content( new ObjectMapper().writeValueAsString(realEstateToSave)))
		.andExpect(status().isBadRequest());
	}
}
