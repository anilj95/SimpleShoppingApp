package com.example.anil.shoppingApplication;

import java.io.Serializable;

public class Speakers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String price;

	private boolean isSelected;

	public Speakers() {

	}

	public Speakers(String name, String emailId) {

		this.name = name;
		this.price = emailId;

	}

	public Speakers(String name, String emailId, boolean isSelected) {

		this.name = name;
		this.price = emailId;
		this.isSelected = isSelected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return price;
	}

	public void setEmailId(String emailId) {
		this.price = emailId;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
