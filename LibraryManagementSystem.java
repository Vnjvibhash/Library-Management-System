import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter a command: ");
            String command = scanner.nextLine();

            if (command.startsWith("add")) {
                String[] parts = command.split(" ");
                int bookId = Integer.parseInt(parts[1]);
                String bookName = parts[2];
                String author = parts[3];
                String publisher = parts[4];
                float price = Float.parseFloat(parts[5]);

                Book book = new Book(bookId, bookName, author, publisher, price);
                library.addBook(book);

                System.out.println("Book added successfully");
            } else if (command.startsWith("remove")) {
                String[] parts = command.split(" ");
                int bookId = Integer.parseInt(parts[1]);

                library.removeBook(bookId);

                System.out.println("Book removed successfully");
            } else if (command.startsWith("find")) {
                String[] parts = command.split(" ");
                int bookId = Integer.parseInt(parts[1]);
                Book book = library.findBook(bookId);

                if (book != null) {
                    System.out.println("Book found:");
                    System.out.println("ID: " + book.getBookId());
                    System.out.println("Name: " + book.getBookName());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Publisher: " + book.getPublisher());
                    System.out.println("Price: " + book.getPrice());
                } else {
                    System.out.println("Book not found");
                }
            } else if (command.startsWith("list")) {
                List<Book> books = library.getAllBooks();

                if (books.size() == 0) {
                    System.out.println("No books found");
                } else {
                    for (Book book : books) {
                        System.out.println("ID: " + book.getBookId());
                        System.out.println("Name: " + book.getBookName());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Publisher: " + book.getPublisher());
                        System.out.println("Price: " + book.getPrice());
                        System.out.println();
                    }
                }
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command");
            }
        }
    }
}

class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String publisher;
    private double price;

    public Book(int bookId, String bookName, String author, String publisher, double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        if (findBook(book.getBookId()) != null) {
            return false;
        }

        return books.add(book);
    }

    public boolean removeBook(int bookId) {
        Book book = findBook(bookId);

        if (book == null) {
            return false;
        }

        return books.remove(book);
    }

    public Book findBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }

        return null;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
