SET SERVEROUTPUT ON;

-- Scenario 1: Calculate the age of customers
CREATE OR REPLACE FUNCTION CalculateAge(p_dob IN DATE) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END CalculateAge;
/

-- Scenario 2: Compute the monthly installment for a loan
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount IN NUMBER,
    p_interest_rate IN NUMBER,
    p_duration_years IN NUMBER
) RETURN NUMBER IS
    v_installment NUMBER;
BEGIN
    v_installment := (p_loan_amount + (p_loan_amount * (p_interest_rate / 100) * p_duration_years)) / (p_duration_years * 12);
    RETURN ROUND(v_installment, 2);
END CalculateMonthlyInstallment;
/

-- Scenario 3: Check if a customer has sufficient balance
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount IN NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END HasSufficientBalance;
/
