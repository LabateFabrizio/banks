package com.example.fabrick.producingwebservice.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fabrick.producingwebservice.model.Account;
import com.example.fabrick.producingwebservice.model.MoneyTransfer;

@Service
public class MoneyTransferService {

	
	@Autowired
    private AccountService accountService;

    public MoneyTransfer makeMoneyTransfer(Long accountId, MoneyTransfer moneyTransfer) {
        // Check if the account exists
        Account account = accountService.getAccountBalance(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        // Check if the money transfer is valid
        if (moneyTransfer == null || moneyTransfer.getCreditorName() == null || moneyTransfer.getAmount() == null) {
            throw new RuntimeException("Invalid money transfer request");
        }

        // Create a new money transfer record
        MoneyTransfer newMoneyTransfer = new MoneyTransfer();
        newMoneyTransfer.setAccountId(accountId);
        newMoneyTransfer.setStatus("PENDING");
        newMoneyTransfer.setDirection("OUTGOING");
        newMoneyTransfer.setCreditorName(moneyTransfer.getCreditorName());
        newMoneyTransfer.setCreditorAccountCode(moneyTransfer.getCreditorAccountCode());
        newMoneyTransfer.setCreditorBicCode(moneyTransfer.getCreditorBicCode());
        newMoneyTransfer.setCreditorAddress(moneyTransfer.getCreditorAddress());
        newMoneyTransfer.setDebtorName(account.getHolderName());
        newMoneyTransfer.setDebtorAccountCode(account.getIban());
        newMoneyTransfer.setCro(UUID.randomUUID().toString());
        newMoneyTransfer.setUri("REMITTANCE_INFORMATION");
        newMoneyTransfer.setTrn(UUID.randomUUID().toString());
        newMoneyTransfer.setDescription(moneyTransfer.getDescription());
        newMoneyTransfer.setCreatedDatetime(ZonedDateTime.now(ZoneId.systemDefault()));
        newMoneyTransfer.setAccountedDatetime(ZonedDateTime.now(ZoneId.systemDefault()));
        newMoneyTransfer.setDebtorValueDate(LocalDate.now());
        newMoneyTransfer.setCreditorValueDate(LocalDate.now());
        newMoneyTransfer.setAmount(moneyTransfer.getAmount());
        newMoneyTransfer.setFeeType(moneyTransfer.getFeeType());
        newMoneyTransfer.setFeeAccountId(moneyTransfer.getFeeAccountId());
        newMoneyTransfer.setHasTaxRelief(moneyTransfer.isHasTaxRelief());

        // Save the money transfer record to the database
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO gbs.money_transfers (account_id, status, direction, creditor_name, creditor_account_code, creditor_bic_code, creditor_address, debtor_name, debtor_account_code, cro, uri, trn, description, created_datetime, accounted_datetime, debtor_value_date, creditor_value_date, amount, fee_type, fee_account_id, has_tax_relief) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, accountId);
            preparedStatement.setString(2, "PENDING");
            preparedStatement.setString(3, "OUTGOING");
            preparedStatement.setString(4, moneyTransfer.getCreditorName());
            preparedStatement.setString(5, moneyTransfer.getCreditorAccountCode());
            preparedStatement.setString(6, moneyTransfer.getCreditorBicCode());
            preparedStatement.setString(7, moneyTransfer.getCreditorAddress());
            preparedStatement.setString(8, account.getHolderName());
            preparedStatement.setString(9, account.getIban());
            preparedStatement.setString(10, newMoneyTransfer.getCro());
            preparedStatement.setString(11, "REMITTANCE_INFORMATION");
            preparedStatement.setString(12, newMoneyTransfer.getTrn());
            preparedStatement.setString(13, moneyTransfer.getDescription());
            preparedStatement.setTimestamp(14, Timestamp.from(newMoneyTransfer.getCreatedDatetime().toInstant()));
            preparedStatement.setTimestamp(15, Timestamp.from(newMoneyTransfer.getAccountedDatetime().toInstant()));
            preparedStatement.setDate(16, Date.valueOf(newMoneyTransfer.getDebtorValueDate()));
            preparedStatement.setDate(17, Date.valueOf(newMoneyTransfer.getCreditorValueDate()));
            preparedStatement.setBigDecimal(18, moneyTransfer.getAmount());
            preparedStatement.setString(19, moneyTransfer.getFeeType());
            preparedStatement.setLong(20, moneyTransfer.getFeeAccountId());
            preparedStatement.setBoolean(21, moneyTransfer.isHasTaxRelief());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving money transfer record", e);
        }

        return newMoneyTransfer;
    }

}
