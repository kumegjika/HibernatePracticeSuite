package Menu;

import Entity.Student;
import Repository.StudentRepository;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    private static final StudentRepository studentRepo = new StudentRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Find Student by ID");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent(scanner);
                    break;
                case "2":
                    updateStudent(scanner);
                    break;
                case "3":
                    deleteStudent(scanner);
                    break;
                case "4":
                    viewAllStudents();
                    break;
                case "5":
                    findStudentById(scanner);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        try {
            Student student = new Student();

            System.out.print("Enter student name: ");
            student.setName(scanner.nextLine());

            System.out.print("Enter student surname: ");
            student.setSurname(scanner.nextLine());

            System.out.print("Enter student email: ");
            student.setEmail(scanner.nextLine());

            studentRepo.save(student);
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding student.");
            e.printStackTrace();
        }
    }

    private static void updateStudent(Scanner scanner) {
        try {
            System.out.print("Enter student ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentRepo.getStudentById(id);

            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.print("New name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) student.setName(name);

            System.out.print("New surname (leave blank to keep current): ");
            String surname = scanner.nextLine();
            if (!surname.isEmpty()) student.setSurname(surname);

            System.out.print("New email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) student.setEmail(email);

            studentRepo.update(student);
            System.out.println("Student updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating student.");
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Scanner scanner) {
        try {
            System.out.print("Enter student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentRepo.getStudentById(id);

            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            studentRepo.delete(student);
            System.out.println("Student deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting student.");
            e.printStackTrace();
        }
    }

    private static void viewAllStudents() {
        List<Student> students = studentRepo.findAll();

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void findStudentById(Scanner scanner) {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentRepo.getStudentById(id);

            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid ID.");
        }
    }
}
