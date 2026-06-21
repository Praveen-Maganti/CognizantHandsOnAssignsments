SET SERVEROUTPUT ON;

-- Scenario 1: Process monthly interest for all savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
END ProcessMonthlyInterest;
/

-- Scenario 2: Implement a bonus scheme for employees
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE Department = p_department;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_percentage || '% applied to department ' || p_department);
END UpdateEmployeeBonus;
/

-- Scenario 3: Transfer funds between accounts 
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account FOR UPDATE;
    
    IF v_balance >= p_amount THEN
        UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
        UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Transfer of ' || p_amount || ' completed successfully.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient balance in source account.');
        ROLLBACK;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Account not found.');
        ROLLBACK;
END TransferFunds;
/
