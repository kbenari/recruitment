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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RealEstateNotFoundException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 5439759551950620748L;

	public RealEstateNotFoundException(String message) {
		super(message);

	}

}
