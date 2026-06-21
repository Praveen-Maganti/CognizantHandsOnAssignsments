public class ProxyPatternExampleMain {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_image.jpg");

        // Image will be loaded from disk
        System.out.println("First call to display:");
        image.display();

        // Image will not be loaded from disk again
        System.out.println("\nSecond call to display:");
        image.display();
    }
}