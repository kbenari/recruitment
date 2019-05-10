/**
 * 
 */
package com.nexity.recruitment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kben
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class RealEstateAlreadyExistingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RealEstateAlreadyExistingException(String message) {
		
		super(message);
	}
	
	

}
