Time Complexity
The recursive method has a time complexity of O(n) because it makes one recursive call for each period.
The space complexity is also O(n) because each recursive call is stored in the call stack.
How to Optimize
Use an iterative loop instead of recursion to reduce space complexity to O(1).
Use memoization if the same calculations are repeated multiple times.
Use the direct formula FV = PV × (1 + r)ⁿ to calculate the result faster without recursion.

This makes the program more efficient and avoids stack overflow for large values of n.