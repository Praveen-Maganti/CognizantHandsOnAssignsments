package EmployeeManagementSystem;

public class EmployeeManagement {
    private Employee[] employees;
    private int size;
    private int capacity;

    public EmployeeManagement(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    // Add employee
    public void addEmployee(Employee employee) {
        if (size == capacity) {
            System.out.println("Array is full. Cannot add more employees.");
            return;
        }
        employees[size] = employee;
        size++;
        System.out.println("Added: " + employee.getName());
    }

    // Search employee by ID
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null; // Not found
    }

    // Traverse array
    public void traverseEmployees() {
        System.out.println("Employee List:");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete employee by ID
    public void deleteEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[size - 1] = null; // Clear the last element
                size--;
                System.out.println("Deleted employee with ID: " + employeeId);
                return;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
    }

    public static void main(String[] args) {
        EmployeeManagement em = new EmployeeManagement(5);
        
        em.addEmployee(new Employee("E01", "John Doe", "Developer", 75000));
        em.addEmployee(new Employee("E02", "Jane Smith", "Manager", 90000));
        
        em.traverseEmployees();
        
        System.out.println("\nSearching for E02:");
        System.out.println(em.searchEmployee("E02"));
        
        System.out.println("\nDeleting E01:");
        em.deleteEmployee("E01");
        
        em.traverseEmployees();
    }
}
