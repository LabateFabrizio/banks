package com.example.fabrick.producingwebservice.model;

import java.math.BigDecimal;

public class Transaction {

	private Long accountId;
	private Long transactionId;
    private String type;
    private BigDecimal amount;
    private String currency;
    private String description;
    

	
	public Transaction(Long accountId, Long transactionId,String type, BigDecimal amount, String currency, String description) {
		super();
		this.accountId = accountId;
		this.transactionId = transactionId;
		this.type = type;
		this.amount = amount;
		this.currency = currency;
		this.description = description;
	}
	
	public Transaction() {
		super();
	}
    
    
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
