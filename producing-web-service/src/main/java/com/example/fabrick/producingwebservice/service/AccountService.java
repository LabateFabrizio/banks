package com.example.fabrick.producingwebservice.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.fabrick.producingwebservice.model.Account;
import com.example.fabrick.producingwebservice.model.MoneyTransfer;
import com.example.fabrick.producingwebservice.model.Transaction;

@Service
public class AccountService {

    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:account", "sa", "")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM gbs.accounts");
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getLong("account_id"));
                account.setIban(resultSet.getString("iban"));
                account.setAbiCode(resultSet.getString("abi_code"));
                account.setCabCode(resultSet.getString("cab_code"));
                account.setCountryCode(resultSet.getString("country_code"));
                account.setInternationalCin(resultSet.getString("international_cin"));
                account.setNationalCin(resultSet.getString("national_cin"));
                account.setAccount(resultSet.getString("account"));
                account.setAlias(resultSet.getString("alias"));
                account.setProductName(resultSet.getString("product_name"));
                account.setHolderName(resultSet.getString("holder_name"));
                account.setActivatedDate(LocalDate.parse(resultSet.getString("activated_date")));
                account.setCurrency(resultSet.getString("currency"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account getAccountBalance(Long accountId) {
        Account account = null;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:account", "sa", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gbs.accounts WHERE account_id = ?");
            preparedStatement.setLong(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setAccountId(resultSet.getLong("account_id"));
                account.setIban(resultSet.getString("iban"));
                account.setAbiCode(resultSet.getString("abi_code"));
                account.setCabCode(resultSet.getString("cab_code"));
                account.setCountryCode(resultSet.getString("country_code"));
                account.setInternationalCin(resultSet.getString("international_cin"));
                account.setNationalCin(resultSet.getString("national_cin"));
                account.setAccount(resultSet.getString("account"));
                account.setAlias(resultSet.getString("alias"));
                account.setProductName(resultSet.getString("product_name"));
                account.setHolderName(resultSet.getString("holder_name"));
                account.setActivatedDate(LocalDate.parse(resultSet.getString("activated_date")));
                account.setCurrency(resultSet.getString("currency"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public MoneyTransfer makeMoneyTransfer(Long accountId, MoneyTransfer moneyTransfer) {
        // Check if the account exists
        Account account = new Account();
        if (account.getAccountId() == null) {
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
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:account", "sa", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO gbs.money_transfers (account_id, status, direction, creditor_name, creditor_account_code, creditor_bic_code, creditor_address, debtor_name, debtor_account_code, cro, uri, trn, description, created_datetime, accounted_datetime, debtor_value_date, creditor_value_date, amount, fee_type, fee_account_id, has_tax_relief) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, accountId);
            preparedStatement.setString(2, "PENDING");
            preparedStatement.setString(3, "OUTGOING");
            preparedStatement.setString(4, moneyTransfer.getCreditorName());
            newMoneyTransfer.setCreditorAccountCode(moneyTransfer.getCreditorAccountCode());
            newMoneyTransfer.setCreditorBicCode(moneyTransfer.getCreditorBicCode());
            newMoneyTransfer.setCreditorAddress(moneyTransfer.getCreditorAddress());
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

    public List<Transaction> getTransactions(Long accountId) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:account", "sa", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gbs.transactions WHERE account_id = ?");
            preparedStatement.setLong(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getLong("transaction_id"));
                transaction.setAccountId(resultSet.getLong("account_id"));
                transaction.setType(resultSet.getString("type"));
                transaction.setAmount(resultSet.getBigDecimal("amount"));
                transaction.setCurrency(resultSet.getString("currency"));
                transaction.setDescription(resultSet.getString("description"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
