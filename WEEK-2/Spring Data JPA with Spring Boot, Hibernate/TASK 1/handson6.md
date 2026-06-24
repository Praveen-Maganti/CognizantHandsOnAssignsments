Importance of @Transactional

@Transactional ensures that all database operations inside a method are executed within a single transaction.

- If all operations are successful, the transaction is committed.
- If any operation fails, the transaction is rolled back.
- Spring automatically manages Hibernate sessions and transactions.

Example:

@Transactional
public void addEmployee(Employee employee) {
    employeeRepository.save(employee);
}

In this example, Spring automatically starts the transaction, saves the employee, commits the transaction if successful, and rolls back if an exception occurs.
