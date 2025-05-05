package Menu;

import Entity.Course;
import Entity.Student;
import Repository.CourseRepository;
import Repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseMenu {
    private static final CourseRepository courseRepo = new CourseRepository();
    private static final StudentRepository studentRepo = new StudentRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- COURSE MANAGEMENT ---");
            System.out.println("1. Add New Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. View All Courses");
            System.out.println("5. Assign Students to Course");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addCourse(scanner);
                    break;
                case "2":
                    updateCourse(scanner);
                    break;
                case "3":
                    deleteCourse(scanner);
                    break;
                case "4":
                    viewAllCourses();
                    break;
                case "5":
                    assignStudentsToCourse(scanner);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addCourse(Scanner scanner) {
        try {
            Course course = new Course();

            System.out.print("Enter course name: ");
            course.setName(scanner.nextLine());

            System.out.print("Enter course description: ");
            course.setDescription(scanner.nextLine());

            System.out.print("Enter course price: ");
            course.setPrice(Double.parseDouble(scanner.nextLine()));

            courseRepo.save(course);
            System.out.println("Course added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding course.");
            e.printStackTrace();
        }
    }

    private static void updateCourse(Scanner scanner) {
        try {
            System.out.print("Enter course ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            Course course = courseRepo.getCourseById(id);

            if (course == null) {
                System.out.println("Course not found.");
                return;
            }

            System.out.print("New name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) course.setName(name);

            System.out.print("New description (leave blank to keep current): ");
            String desc = scanner.nextLine();
            if (!desc.isEmpty()) course.setDescription(desc);

            System.out.print("New price (leave blank to keep current): ");
            String priceInput = scanner.nextLine();
            if (!priceInput.isEmpty()) {
                course.setPrice(Double.parseDouble(priceInput));
            }

            courseRepo.update(course);
            System.out.println("Course updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating course.");
            e.printStackTrace();
        }
    }

    private static void deleteCourse(Scanner scanner) {
        try {
            System.out.print("Enter course ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            Course course = courseRepo.getCourseById(id);

            if (course == null) {
                System.out.println("Course not found.");
                return;
            }

            courseRepo.delete(course);
            System.out.println("Course deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting course.");
            e.printStackTrace();
        }
    }

    private static void viewAllCourses() {
        List<Course> courses = courseRepo.findAll();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    private static void assignStudentsToCourse(Scanner scanner) {
        try {
            System.out.print("Enter course ID: ");
            int courseId = Integer.parseInt(scanner.nextLine());
            Course course = courseRepo.getCourseById(courseId);

            if (course == null) {
                System.out.println("Course not found.");
                return;
            }

            List<Student> allStudents = studentRepo.findAll();
            if (allStudents.isEmpty()) {
                System.out.println("No students available.");
                return;
            }

            System.out.println("Available students:");
            allStudents.forEach(student -> System.out.println(student.getId() + " - " + student.getName()));

            List<Student> assignedStudents = new ArrayList<>();
            while (true) {
                System.out.print("Enter student ID to assign (0 to finish): ");
                int studentId = Integer.parseInt(scanner.nextLine());
                if (studentId == 0) break;

                Student student = studentRepo.getStudentById(studentId);
                if (student != null) {
                    assignedStudents.add(student);
                } else {
                    System.out.println("Student not found.");
                }
            }

            course.setServices(assignedStudents);
            courseRepo.update(course);
            System.out.println("Students assigned to course.");
        } catch (Exception e) {
            System.out.println("Error assigning students.");
            e.printStackTrace();
        }
    }
}
