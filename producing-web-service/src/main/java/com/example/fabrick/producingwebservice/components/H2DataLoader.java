package com.example.fabrick.producingwebservice.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class H2DataLoader implements ApplicationRunner  {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create schema if not exists
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS gbs");

        // Create accounts table
        jdbcTemplate.execute("CREATE TABLE gbs.accounts (account_id BIGINT PRIMARY KEY AUTO_INCREMENT,\r\n"
                + "    iban VARCHAR(34),\r\n"
                + "    abi_code VARCHAR(6),\r\n"
                + "    cab_code VARCHAR(5),\r\n"
                + "    country_code VARCHAR(2),\r\n"
                + "    international_cin VARCHAR(2),\r\n"
                + "    national_cin VARCHAR(1),\r\n"
                + "    account VARCHAR(16),\r\n"
                + "    alias VARCHAR(50),\r\n"
                + "    product_name VARCHAR(50),\r\n"
                + "    holder_name VARCHAR(50),\r\n"
                + "    activated_date DATE,\r\n"
                + "    currency VARCHAR(3))");

        // Create transactions table
        jdbcTemplate.execute("CREATE TABLE gbs.transactions (transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,\r\n"
                + "    account_id BIGINT,\r\n"
                + "    type VARCHAR(10),\r\n"
                + "    amount DECIMAL(15, 2),\r\n"
                + "    currency VARCHAR(3),\r\n"
                + "    description VARCHAR(100),\r\n"
                + "    FOREIGN KEY (account_id) REFERENCES gbs.accounts (account_id))");

        // Create money_transfers table
        jdbcTemplate.execute("CREATE TABLE gbs.money_transfers (money_transfer_id BIGINT PRIMARY KEY AUTO_INCREMENT,\r\n"
                + "    account_id BIGINT,\r\n"
                + "    status VARCHAR(10),\r\n"
                + "    direction VARCHAR(10),\r\n"
                + "    creditor_name VARCHAR(50),\r\n"
                + "    creditor_account_code VARCHAR(27),\r\n"
                + "    creditor_bic_code VARCHAR(11),\r\n"
                + "    creditor_address VARCHAR(100),\r\n"
                + "    debtor_name VARCHAR(50),\r\n"
                + "    debtor_account_code VARCHAR(27),\r\n"
                + "    cro VARCHAR(17),\r\n"
                + "    uri VARCHAR(50),\r\n"
                + "    trn VARCHAR(25),\r\n"
                + "    description VARCHAR(100),\r\n"
                + "    created_datetime TIMESTAMP,\r\n"
                + "    accounted_datetime TIMESTAMP,\r\n"
                + "    debtor_value_date DATE,\r\n"
                + "    creditor_value_date DATE,\r\n"
                + "    amount DECIMAL(15, 2),\r\n"
                + "    fee_type VARCHAR(10),\r\n"
                + "    fee_account_id BIGINT,\r\n"
                + "    has_tax_relief BOOLEAN,\r\n"
                + "    FOREIGN KEY (account_id) REFERENCES gbs.accounts (account_id))");
    }

	/*@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS gbs");
        jdbcTemplate.execute("CREATE TABLE gbs.accounts (account_id BIGINT PRIMARY KEY,\r\n"
        		+ "    iban VARCHAR(34),\r\n"
        		+ "    abi_code VARCHAR(6),\r\n"
        		+ "    cab_code VARCHAR(5),\r\n"
        		+ "    country_code VARCHAR(2),\r\n"
        		+ "    international_cin VARCHAR(2),\r\n"
        		+ "    national_cin VARCHAR(1),\r\n"
        		+ "    account VARCHAR(16),\r\n"
        		+ "    alias VARCHAR(50),\r\n"
        		+ "    product_name VARCHAR(50),\r\n"
        		+ "    holder_name VARCHAR(50),\r\n"
        		+ "    activated_date DATE,\r\n"
        		+ "    currency VARCHAR(3))");
        jdbcTemplate.execute("CREATE TABLE gbs.transactions (transaction_id BIGINT PRIMARY KEY IDENTITY,\r\n"
        		+ "    account_id BIGINT,\r\n"
        		+ "    type VARCHAR(10),\r\n"
        		+ "    amount DECIMAL(15, 2),\r\n"
        		+ "    currency VARCHAR(3),\r\n"
        		+ "    description VARCHAR(100),\r\n"
        		+ "    FOREIGN KEY (account_id) REFERENCES gbs.accounts (account_id))");
        jdbcTemplate.execute("CREATE TABLE gbs.money_transfers (money_transfer_id BIGINT PRIMARY KEY IDENTITY,\r\n"
        		+ "    account_id BIGINT,\r\n"
        		+ "    status VARCHAR(10),\r\n"
        		+ "    direction VARCHAR(10),\r\n"
        		+ "    creditor_name VARCHAR(50),\r\n"
        		+ "    creditor_account_code VARCHAR(27),\r\n"
        		+ "    creditor_bic_code VARCHAR(11),\r\n"
        		+ "    creditor_address VARCHAR(100),\r\n"
        		+ "    debtor_name VARCHAR(50),\r\n"
        		+ "    debtor_account_code VARCHAR(27),\r\n"
        		+ "    cro VARCHAR(17),\r\n"
        		+ "    uri VARCHAR(50),\r\n"
        		+ "    trn VARCHAR(25),\r\n"
        		+ "    description VARCHAR(100),\r\n"
        		+ "    created_datetime TIMESTAMP,\r\n"
        		+ "    accounted_datetime TIMESTAMP,\r\n"
        		+ "    debtor_value_date DATE,\r\n"
        		+ "    creditor_value_date DATE,\r\n"
        		+ "    amount DECIMAL(15, 2),\r\n"
        		+ "    fee_type VARCHAR(10),\r\n"
        		+ "    fee_account_id BIGINT,\r\n"
        		+ "    has_tax_relief BOOLEAN,\r\n"
        		+ "    FOREIGN KEY (account_id) REFERENCES gbs.accounts (account_id))");
	}*/
    
    

    
}
