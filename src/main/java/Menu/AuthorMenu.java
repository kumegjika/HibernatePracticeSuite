package Menu;

import Entity.Author;
import Repository.AuthorRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AuthorMenu {
    private static final AuthorRepository authorRepo = new AuthorRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- AUTHOR MANAGEMENT ---");
            System.out.println("1. Add New Author");
            System.out.println("2. Update Author Details");
            System.out.println("3. Delete Author");
            System.out.println("4. View Author List");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addAuthor(scanner);
                    break;
                case "2":
                    updateAuthor(scanner);
                    break;
                case "3":
                    deleteAuthor(scanner);
                    break;
                case "4":
                    viewAuthorList();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addAuthor(Scanner scanner) {
        try {
            Author author = new Author();
            System.out.print("Enter author's name: ");
            author.setName(scanner.nextLine());

            System.out.print("Enter author's surname: ");
            author.setSurname(scanner.nextLine());

            System.out.print("Enter author's email: ");
            author.setEmail(scanner.nextLine());

            System.out.print("Enter author's birthday (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);
            author.setBirthday(birthday);

            authorRepo.save(author);
            System.out.println("Author added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding author.");
            e.printStackTrace();
        }
    }

    private static void updateAuthor(Scanner scanner) {
        try {
            System.out.print("Enter author ID to update: ");
            int authorId = Integer.parseInt(scanner.nextLine());

            Author author = authorRepo.getAuthorById(authorId);
            if (author == null) {
                System.out.println("Author not found.");
                return;
            }

            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                author.setName(name);
            }

            System.out.print("Enter new surname (leave blank to keep current): ");
            String surname = scanner.nextLine();
            if (!surname.isEmpty()) {
                author.setSurname(surname);
            }

            System.out.print("Enter new email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                author.setEmail(email);
            }

            System.out.print("Enter new birthday (yyyy-MM-dd) or leave blank to keep current: ");
            String birthdayInput = scanner.nextLine();
            if (!birthdayInput.isEmpty()) {
                Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayInput);
                author.setBirthday(birthday);
            }

            authorRepo.update(author);
            System.out.println("Author updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating author.");
            e.printStackTrace();
        }
    }

    private static void deleteAuthor(Scanner scanner) {
        try {
            System.out.print("Enter author ID to delete: ");
            int authorId = Integer.parseInt(scanner.nextLine());

            Author author = authorRepo.getAuthorById(authorId);
            if (author == null) {
                System.out.println("Author not found.");
                return;
            }

            authorRepo.delete(author);
            System.out.println("Author deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting author.");
            e.printStackTrace();
        }
    }

    private static void viewAuthorList() {
        List<Author> authors = authorRepo.findAll();

        if (authors.isEmpty()) {
            System.out.println("No authors found.");
        } else {
            authors.forEach(System.out::println);
        }
    }
}
