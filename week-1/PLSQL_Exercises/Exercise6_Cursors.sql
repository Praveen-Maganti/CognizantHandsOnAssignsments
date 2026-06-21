SET SERVEROUTPUT ON;

-- Scenario 1: Generate monthly statements for all customers
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
    
    v_name Customers.Name%TYPE;
    v_date Transactions.TransactionDate%TYPE;
    v_amount Transactions.Amount%TYPE;
    v_type Transactions.TransactionType%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Monthly Statements ---');
    OPEN GenerateMonthlyStatements;
    LOOP
        FETCH GenerateMonthlyStatements INTO v_name, v_date, v_amount, v_type;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Customer: ' || v_name || ' | Date: ' || TO_CHAR(v_date, 'YYYY-MM-DD') || ' | Type: ' || v_type || ' | Amount: $' || v_amount);
    END LOOP;
    CLOSE GenerateMonthlyStatements;
END;
/

-- Scenario 2: Apply annual fee to all accounts
DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts;
        
    v_account_id Accounts.AccountID%TYPE;
    v_balance Accounts.Balance%TYPE;
    v_fee CONSTANT NUMBER := 50; -- Example $50 annual fee
BEGIN
    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO v_account_id, v_balance;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;
        
        UPDATE Accounts
        SET Balance = Balance - v_fee
        WHERE AccountID = v_account_id;
        
        DBMS_OUTPUT.PUT_LINE('Applied annual fee of $' || v_fee || ' to Account ' || v_account_id);
    END LOOP;
    CLOSE ApplyAnnualFee;
    COMMIT;
END;
/

-- Scenario 3: Update the interest rate for all loans based on a new policy
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans;
        
    v_loan_id Loans.LoanID%TYPE;
    v_interest_rate Loans.InterestRate%TYPE;
    v_new_rate NUMBER;
BEGIN
    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO v_loan_id, v_interest_rate;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;
        
        -- New policy example: increase rate by 0.5%
        v_new_rate := v_interest_rate + 0.5;
        
        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE LoanID = v_loan_id;
        
        DBMS_OUTPUT.PUT_LINE('Updated Loan ' || v_loan_id || ' interest rate to ' || v_new_rate || '%');
    END LOOP;
    CLOSE UpdateLoanInterestRates;
    COMMIT;
END;
/
