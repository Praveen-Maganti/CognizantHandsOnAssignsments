public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        // Mock database call
        if (id.equals("1")) {
            return "Customer 1: John Doe";
        }
        return "Customer not found";
    }
}