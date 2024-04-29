package com.example.fabrick.producingwebservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class MoneyTransfer {
    private Long accountId;
    private String status;
    private String direction;
    private String creditorName;
    private String creditorAccountCode;
    private String creditorBicCode;
    private String creditorAddress;
    private String debtorName;
    private String debtorAccountCode;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private ZonedDateTime createdDatetime;
    private ZonedDateTime accountedDatetime;
    private LocalDate debtorValueDate;
    private LocalDate creditorValueDate;
    private BigDecimal amount;
    private String feeType;
    private Long feeAccountId;
    private boolean hasTaxRelief;
    
    

    public MoneyTransfer() {
		super();
	}

	public MoneyTransfer(Long accountId, String status, String direction, String creditorName,
			String creditorAccountCode, String creditorBicCode, String creditorAddress, String debtorName,
			String debtorAccountCode, String cro, String uri, String trn, String description,
			ZonedDateTime createdDatetime, ZonedDateTime accountedDatetime, LocalDate debtorValueDate,
			LocalDate creditorValueDate, BigDecimal amount, String feeType, Long feeAccountId, boolean hasTaxRelief) {
		super();
		this.accountId = accountId;
		this.status = status;
		this.direction = direction;
		this.creditorName = creditorName;
		this.creditorAccountCode = creditorAccountCode;
		this.creditorBicCode = creditorBicCode;
		this.creditorAddress = creditorAddress;
		this.debtorName = debtorName;
		this.debtorAccountCode = debtorAccountCode;
		this.cro = cro;
		this.uri = uri;
		this.trn = trn;
		this.description = description;
		this.createdDatetime = createdDatetime;
		this.accountedDatetime = accountedDatetime;
		this.debtorValueDate = debtorValueDate;
		this.creditorValueDate = creditorValueDate;
		this.amount = amount;
		this.feeType = feeType;
		this.feeAccountId = feeAccountId;
		this.hasTaxRelief = hasTaxRelief;
	}

	// Getters and setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getCreditorAccountCode() {
        return creditorAccountCode;
    }

    public void setCreditorAccountCode(String creditorAccountCode) {
        this.creditorAccountCode = creditorAccountCode;
    }

    public String getCreditorBicCode() {
        return creditorBicCode;
    }

    public void setCreditorBicCode(String creditorBicCode) {
        this.creditorBicCode = creditorBicCode;
    }

    public String getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(String creditorAddress) {
        this.creditorAddress = creditorAddress;
    }

	public String getDebtorName() {
		return debtorName;
	}

	public void setDebtorName(String debtorName) {
		this.debtorName = debtorName;
	}

	public String getDebtorAccountCode() {
		return debtorAccountCode;
	}

	public void setDebtorAccountCode(String debtorAccountCode) {
		this.debtorAccountCode = debtorAccountCode;
	}

	public String getCro() {
		return cro;
	}

	public void setCro(String cro) {
		this.cro = cro;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTrn() {
		return trn;
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(ZonedDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public ZonedDateTime getAccountedDatetime() {
		return accountedDatetime;
	}

	public void setAccountedDatetime(ZonedDateTime accountedDatetime) {
		this.accountedDatetime = accountedDatetime;
	}

	public LocalDate getDebtorValueDate() {
		return debtorValueDate;
	}

	public void setDebtorValueDate(LocalDate debtorValueDate) {
		this.debtorValueDate = debtorValueDate;
	}

	public LocalDate getCreditorValueDate() {
		return creditorValueDate;
	}

	public void setCreditorValueDate(LocalDate creditorValueDate) {
		this.creditorValueDate = creditorValueDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Long getFeeAccountId() {
		return feeAccountId;
	}

	public void setFeeAccountId(Long feeAccountId) {
		this.feeAccountId = feeAccountId;
	}

	public boolean isHasTaxRelief() {
		return hasTaxRelief;
	}

	public void setHasTaxRelief(boolean hasTaxRelief) {
		this.hasTaxRelief = hasTaxRelief;
	}

    
}