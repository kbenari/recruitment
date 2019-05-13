/**
 * 
 */
package com.nexity.recruitment.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nexity.recruitment.model.RealEstate;

/**
 * @author kben
 *
 */

@Repository()
public class RealEstateDaoImpl implements RealEstateDao{

private static  Integer index= 2;
private static Map<Integer, RealEstate> realEstates= new HashMap<>();
static {
	realEstates.put(1, new RealEstate(1,"appartement", "exemple d'appartement","20 rue GateVigne 78200 Mantes la jolie",45,new BigDecimal (145000)));
	realEstates.put(2, new RealEstate(2,"immeuble", "exemple d'immeuble","19 Rue de Vienne, 75801 Paris",1235,new BigDecimal (12000000)));
	
}
	
	@Override
	public List<RealEstate> findAll() {
		return realEstates.values().stream().map(t -> new RealEstate(t)).collect(Collectors.toList());
	}

	@Override
	public RealEstate findById(int id) {
		
		RealEstate foundRealEstate = realEstates.get(id);
		if (foundRealEstate!=null)
		return new RealEstate(foundRealEstate);
		
		return null;
	}

	@Override
	public RealEstate save(RealEstate realEstate) {
		realEstates.put(realEstate.getIndex(), realEstate);
		return new RealEstate(realEstate) ;
	}

	@Override
	public Integer increments() {
		index++;
		return index;
	}

}
