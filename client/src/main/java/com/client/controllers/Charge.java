package com.client.controllers;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Charge implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String cardOwner;
	private String cardNumber;
	private String cardExpMonth;
	private String cardExYear;
	private String cardCode;
	public String getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public String getCardExYear() {
		return cardExYear;
	}

	public void setCardExYear(String cardExYear) {
		this.cardExYear = cardExYear;
	}

	private String cardType;
	
	public Charge () {
		
	}

	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "Charge [cardOwner=" + cardOwner + ", cardNumber=" + cardNumber + ", cardExpMonth=" + cardExpMonth
				+ ", cardExYear=" + cardExYear + ", cardCode=" + cardCode + ", cardType=" + cardType + "]";
	}
	

	
}

