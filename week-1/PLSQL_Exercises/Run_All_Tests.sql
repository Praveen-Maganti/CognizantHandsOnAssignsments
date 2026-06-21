SET SERVEROUTPUT ON;
SET DEFINE OFF;

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- TESTING EXERCISE 2: Error Handling ---');
    -- Scenario 1: SafeTransferFunds (Should succeed)
    SafeTransferFunds(1, 2, 100);
    
    -- Scenario 1: SafeTransferFunds (Should fail due to insufficient funds)
    SafeTransferFunds(1, 2, 100000);

    -- Scenario 2: UpdateSalary
    UpdateSalary(1, 10); -- 10% raise for Alice

    -- Scenario 3: AddNewCustomer
    AddNewCustomer(4, 'Test User', TO_DATE('1995-01-01', 'YYYY-MM-DD'), 500);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- TESTING EXERCISE 3: Stored Procedures ---');
    ProcessMonthlyInterest();
    UpdateEmployeeBonus('IT', 5);
    TransferFunds(1, 2, 50);
END;
/

DECLARE
    v_age NUMBER;
    v_installment NUMBER;
    v_has_balance BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- TESTING EXERCISE 4: Functions ---');
    v_age := CalculateAge(TO_DATE('1990-01-01', 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Calculated Age: ' || v_age);
    
    v_installment := CalculateMonthlyInstallment(5000, 5, 2);
    DBMS_OUTPUT.PUT_LINE('Calculated Monthly Installment: $' || v_installment);
    
    v_has_balance := HasSufficientBalance(1, 100);
    IF v_has_balance THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 DOES NOT have sufficient balance.');
    END IF;
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- TESTING EXERCISE 5: Triggers ---');
    -- Scenario 1: Trigger UpdateCustomerLastModified
    UPDATE Customers SET Balance = 2000 WHERE CustomerID = 1;
    DBMS_OUTPUT.PUT_LINE('Customer 1 updated. Trigger should have changed LastModified.');
    
    -- Scenario 2 and 3: Insert valid deposit (Should succeed and create AuditLog)
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (99, 1, SYSDATE, 50, 'Deposit');
    DBMS_OUTPUT.PUT_LINE('Deposit inserted successfully.');
    
    -- Scenario 3: Insert invalid withdrawal (Should throw an error and halt execution of this block)
END;
/

DECLARE
    v_bal NUMBER;
    v_ann_salary NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- TESTING EXERCISE 7: Packages ---');
    -- CustomerManagement Package
    CustomerManagement.UpdateCustomerDetails(2, 'Jane Smith Updated', 2500);
    v_bal := CustomerManagement.GetCustomerBalance(2);
    DBMS_OUTPUT.PUT_LINE('Package Test - Customer 2 Balance: ' || v_bal);
    
    -- EmployeeManagement Package
    v_ann_salary := EmployeeManagement.CalculateAnnualSalary(1);
    DBMS_OUTPUT.PUT_LINE('Package Test - Employee 1 Annual Salary: ' || v_ann_salary);
END;
/
