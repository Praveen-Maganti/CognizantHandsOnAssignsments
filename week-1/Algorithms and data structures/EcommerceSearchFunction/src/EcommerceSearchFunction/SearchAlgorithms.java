package EcommerceSearchFunction;

import java.util.Arrays;

public class SearchAlgorithms {

    // Linear Search: O(n) time complexity
    public static Product linearSearch(Product[] products, String targetProductId) {
        for (Product product : products) {
            if (product.getProductId().equals(targetProductId)) {
                return product;
            }
        }
        return null; // Not found
    }

    // Binary Search: O(log n) time complexity
    // Array must be sorted by productId beforehand
    public static Product binarySearch(Product[] products, String targetProductId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId().compareTo(targetProductId);

            if (comparison == 0) {
                return products[mid];
            }
            if (comparison < 0) {
                left = mid + 1; // target is greater, ignore left half
            } else {
                right = mid - 1; // target is smaller, ignore right half
            }
        }
        return null; // Not found
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P005", "Smartphone", "Electronics"),
            new Product("P002", "Laptop", "Electronics"),
            new Product("P001", "Desk Chair", "Furniture"),
            new Product("P004", "Coffee Maker", "Home Appliances"),
            new Product("P003", "Notebook", "Stationery")
        };

        // Linear Search
        System.out.println("Linear Search for P004: " + linearSearch(products, "P004"));

        // Binary Search requires a sorted array
        Arrays.sort(products);
        System.out.println("Binary Search for P004 (after sorting): " + binarySearch(products, "P004"));
    }
}
