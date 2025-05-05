package Menu;

import Entity.Author;
import Entity.Book;
import Repository.AuthorRepository;
import Repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookMenu {
    private static final BookRepository bookRepo = new BookRepository();
    private static final AuthorRepository authorRepo = new AuthorRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- BOOK MANAGEMENT ---");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. View Books by Author");
            System.out.println("4. View Book by ID");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBook(scanner);
                    break;
                case "2":
                    viewAllBooks();
                    break;
                case "3":
                    viewBooksByAuthor(scanner);
                    break;
                case "4":
                    viewBookById(scanner);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        try {
            Book book = new Book();

            System.out.print("Enter book title: ");
            book.setTitle(scanner.nextLine());

            System.out.print("Enter book description: ");
            book.setDescription(scanner.nextLine());

            System.out.print("Enter book price: ");
            book.setPrice(Double.parseDouble(scanner.nextLine()));

            System.out.print("Enter number of pages: ");
            book.setPages(Integer.parseInt(scanner.nextLine()));

            System.out.print("Enter publication date (yyyy-MM-dd): ");
            book.setPublicationDate(LocalDate.parse(scanner.nextLine()));

            System.out.print("Enter author ID: ");
            int authorId = Integer.parseInt(scanner.nextLine());
            Author author = authorRepo.getAuthorById(authorId);

            if (author == null) {
                System.out.println("Author not found.");
                return;
            }

            book.setAuthor(author);
            bookRepo.save(book);
            System.out.println("Book added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding book.");
            e.printStackTrace();
        }
    }

    private static void viewAllBooks() {
        List<Book> books = bookRepo.findAll();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void viewBooksByAuthor(Scanner scanner) {
        try {
            System.out.print("Enter author ID: ");
            int authorId = Integer.parseInt(scanner.nextLine());
            List<Book> books = bookRepo.findByAuthorId(authorId);

            if (books.isEmpty()) {
                System.out.println("No books found for this author.");
            } else {
                books.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving books.");
            e.printStackTrace();
        }
    }

    private static void viewBookById(Scanner scanner) {
        try {
            System.out.print("Enter book ID: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = bookRepo.getBookById(bookId);

            if (book == null) {
                System.out.println("Book not found.");
            } else {
                System.out.println(book);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving book.");
            e.printStackTrace();
        }
    }
}
