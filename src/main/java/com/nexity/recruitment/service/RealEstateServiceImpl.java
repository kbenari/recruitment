/**
 * 
 */
package com.nexity.recruitment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexity.recruitment.Exception.RealEstateAlreadyExistingException;
import com.nexity.recruitment.Exception.RealEstateNotFoundException;
import com.nexity.recruitment.dao.RealEstateDao;
import com.nexity.recruitment.model.RealEstate;

/**
 * @author kben
 *
 */

@Service
public class RealEstateServiceImpl implements RealEstateService {

	@Autowired
	RealEstateDao realEstateDao;

	@Override
	public List<RealEstate> findAll() {

		return realEstateDao.findAll();
	}

	@Override
	public RealEstate findById(int id) {

		RealEstate realEstateFound = realEstateDao.findById(id);
		if (realEstateFound == null)
			throw new RealEstateNotFoundException("Annonce immobiliere avec id " + id + " inexistante");

		return realEstateFound;
	}

	@Override
	public RealEstate save(RealEstate realEstate) {

		if (realEstate.getId() == null) {
			realEstate.setId(realEstateDao.increments());
		} else {
			if (findById(realEstate.getId()) != null)
				throw new RealEstateAlreadyExistingException(
						"Annonce immobiliere avec id " + realEstate.getId() + " existe deja");
		}

		return realEstateDao.save(realEstate);
	}

}
