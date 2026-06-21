SET SERVEROUTPUT ON;

-- Scenario 1: Apply a discount to loan interest rates for customers above 60 years old.
DECLARE
    v_age NUMBER;
BEGIN
    FOR loan_rec IN (SELECT l.LoanID, c.DOB FROM Loans l JOIN Customers c ON l.CustomerID = c.CustomerID) LOOP
        -- Calculate age in years
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, loan_rec.DOB) / 12);
        
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = loan_rec.LoanID;
            DBMS_OUTPUT.PUT_LINE('Discount applied for Loan ID: ' || loan_rec.LoanID);
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote to VIP status based on balance over $10,000
BEGIN
    FOR cust_rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust_rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Customer ID ' || cust_rec.CustomerID || ' promoted to VIP.');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Send reminders for loans due within the next 30 days
BEGIN
    FOR due_loan IN (
        SELECT l.LoanID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || due_loan.Name || ', your loan (ID: ' || due_loan.LoanID || ') is due on ' || TO_CHAR(due_loan.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/
