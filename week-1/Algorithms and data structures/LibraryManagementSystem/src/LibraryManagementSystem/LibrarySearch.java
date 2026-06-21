package LibraryManagementSystem;

import java.util.Arrays;

public class LibrarySearch {

    // Linear Search for title
    public static Book linearSearch(Book[] books, String targetTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search for title (assumes sorted array)
    public static Book binarySearch(Book[] books, String targetTitle) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (comparison == 0) {
                return books[mid];
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book("B01", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B02", "1984", "George Orwell"),
            new Book("B03", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B04", "Pride and Prejudice", "Jane Austen")
        };

        System.out.println("Linear Search for '1984': " + linearSearch(books, "1984"));

        Arrays.sort(books); // Sort by title for binary search

        System.out.println("Binary Search for '1984' (after sorting): " + binarySearch(books, "1984"));
    }
}
