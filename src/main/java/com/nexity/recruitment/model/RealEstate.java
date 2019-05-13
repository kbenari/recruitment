package com.nexity.recruitment.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RealEstate extends ResourceSupport {
	
	private Integer index;
	
	@JsonProperty("titre")
	@NotEmpty(message="Merci de fournir un 'titre'")
	private String title;
	
	@JsonProperty("descriptif")
	private String description;
	
	@JsonProperty("adresse")
	@NotEmpty(message="Merci de fournir une 'adresse'")
	private String address;
	
	
	@NotNull(message="Merci de fournir une 'surface'")
	private double surface;
	
	@JsonProperty("prix")
	@NotNull(message="Merci de fournir un 'prix'")
	@DecimalMin("1.00")
	private BigDecimal price;

	/**
	 * constructor using filed
	 * @param id
	 * @param title
	 * @param description
	 * @param address
	 * @param surface
	 * @param price
	 */
	public RealEstate(Integer id, String title, String description, String address, double surface, BigDecimal price) {
		super();
		this.index = id;
		this.title = title;
		this.description = description;
		this.address = address;
		this.surface = surface;
		this.price = price;
	}
	

	/**
	 * constructor by copy used to defance getter
	 * @param realEstate
	 */
	public RealEstate(RealEstate realEstate ) {
		
		this(realEstate.index,realEstate.title,realEstate. description, realEstate.address, realEstate.surface,realEstate.price);
	}


	/**
	 * @return the id
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param id the id to set
	 */
	public void setIndex(Integer id) {
		this.index = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the surface
	 */
	public double getSurface() {
		return surface;
	}

	/**
	 * @param surface the surface to set
	 */
	public void setSurface(double surface) {
		this.surface = surface;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		long temp;
		temp = Double.doubleToLongBits(surface);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RealEstate other = (RealEstate) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (Double.doubleToLongBits(surface) != Double.doubleToLongBits(other.surface))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
