-- Drop tables if they exist
DROP TABLE IF EXISTS banking_fee;
DROP TABLE IF EXISTS portfolio;
DROP TABLE IF EXISTS account;

-- Create tables
CREATE TABLE account (
    account_number VARCHAR(255) PRIMARY KEY
);

CREATE TABLE portfolio (
    portfolio_number VARCHAR(255) PRIMARY KEY,
    account_number VARCHAR(255),
    FOREIGN KEY (account_number) REFERENCES account(account_number)
);

CREATE TABLE banking_fee (
    fee_id INT PRIMARY KEY,
    list_price DECIMAL(10, 2),
    effective_price DECIMAL(10, 2),
    fee_type_code VARCHAR(255),
    fee_type_desc VARCHAR(255),
    portfolio_number VARCHAR(255),
    FOREIGN KEY (portfolio_number) REFERENCES portfolio(portfolio_number)
);

-- Insert sample data
INSERT INTO account (account_number) VALUES ('SG04258000');

INSERT INTO portfolio (portfolio_number, account_number) VALUES ('SG04258000-01', 'SG04258000');
-- INSERT INTO portfolio (portfolio_number, account_number) VALUES ('SG04258000-02', 'SG04258000');

-- Sample banking fees for portfolio 01
INSERT INTO banking_fee (fee_id, list_price, effective_price, fee_type_code, fee_type_desc, portfolio_number) VALUES (1, 3000.00, 3000.00, 'BANKING_SERVICE_FEE', 'BANKING_SERVICE_FEE', 'SG04258000-01');
INSERT INTO banking_fee (fee_id, list_price, effective_price, fee_type_code, fee_type_desc, portfolio_number) VALUES (2, 700.00, 700.00, 'SPECIAL_MAILING', 'SPECIAL_MAILING', 'SG04258000-01');
INSERT INTO banking_fee (fee_id, list_price, effective_price, fee_type_code, fee_type_desc, portfolio_number) VALUES (3, 240.00, 240.00, 'REPORTING_FEE', 'REPORTING_FEE', 'SG04258000-01');

-- -- Sample banking fees for portfolio 02
-- INSERT INTO banking_fee (fee_id, list_price, effective_price, fee_type_code, fee_type_desc, portfolio_number) VALUES (4, 233.00, 230.00, 'BANKING_SERVICE_FEE', 'BANKING_SERVICE_FEE', 'SG04258000-02');
-- INSERT INTO banking_fee (fee_id, list_price, effective_price, fee_type_code, fee_type_desc, portfolio_number) VALUES (5, 722.00, 700.00, 'SPECIAL_MAILING', 'SPECIAL_MAILING', 'SG04258000-02');
-- INSERT INTO banking_fee (fee_id, list_price, effective_price, fee_type_code, fee_type_desc, portfolio_number) VALUES (6, 200.00, 190.00, 'REPORTING_FEE', 'REPORTING_FEE', 'SG04258000-02');