package SortingCustomerOrders;

public class SortAlgorithms {

    // Bubble Sort
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() < orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j+1] to sort in descending order
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) break;
        }
    }

    // Quick Sort
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); // index of larger element
        for (int j = low; j < high; j++) {
            // Sort in descending order
            if (orders[j].getTotalPrice() >= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] ordersForBubble = {
            new Order("O1", "Alice", 250.50),
            new Order("O2", "Bob", 150.00),
            new Order("O3", "Charlie", 500.75),
            new Order("O4", "Dave", 50.25)
        };

        Order[] ordersForQuick = ordersForBubble.clone();

        System.out.println("Applying Bubble Sort...");
        bubbleSort(ordersForBubble);
        for (Order o : ordersForBubble) {
            System.out.println(o);
        }

        System.out.println("\nApplying Quick Sort...");
        quickSort(ordersForQuick, 0, ordersForQuick.length - 1);
        for (Order o : ordersForQuick) {
            System.out.println(o);
        }
    }
}
