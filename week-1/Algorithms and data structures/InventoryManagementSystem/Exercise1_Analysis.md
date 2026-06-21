A HashMap is used because it helps find products quickly using their product ID.

Time Complexity
Add Product: O(1)
Update Product: O(1)
Delete Product: O(1)
Why is it Suitable?

HashMap makes adding, updating, and deleting products very fast, which is important when managing a large inventory.

Optimization

The performance is already very good. If many users access the inventory at the same time, ConcurrentHashMap can be used for better handling of multiple users.