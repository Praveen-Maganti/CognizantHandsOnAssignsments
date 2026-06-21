package InventoryManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        // Using HashMap for O(1) average time complexity for add, update, and delete
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists.");
            return;
        }
        products.put(product.getProductId(), product);
        System.out.println("Product added: " + product.getProductName());
    }

    public void updateProduct(String productId, Product updatedProduct) {
        if (!products.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " not found.");
            return;
        }
        products.put(productId, updatedProduct);
        System.out.println("Product updated: " + updatedProduct.getProductName());
    }

    public void deleteProduct(String productId) {
        if (!products.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " not found.");
            return;
        }
        products.remove(productId);
        System.out.println("Product deleted: " + productId);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }
    
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Product p : products.values()) {
            System.out.println(p);
        }
    }
}
