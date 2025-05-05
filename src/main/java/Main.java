import java.util.Scanner;
import Menu.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====== HibernatePracticeSuite ======");
            System.out.println("1. Author ");
            System.out.println("2. Book ");
            System.out.println("3. Course ");
            System.out.println("4. Student ");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    AuthorMenu.start(scanner);
                    break;
                case "2":
                    BookMenu.start(scanner);
                    break;
                case "3":
                    CourseMenu.start(scanner);
                    break;
                case "4":
                    StudentMenu.start(scanner);
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
