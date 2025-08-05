import dao.StudentDAO;
import model.Student;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=== Student Management System ===");

        while (running) {
            showMenu();
            int option = readInt("Choose an option: ");

            switch (option) {
                case 1 -> insertStudent();
                case 2 -> listStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
        System.out.println("Bye!");
    }

    private static void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add new student");
        System.out.println("2. List all students");
        System.out.println("3. Update student");
        System.out.println("4. Delete student");
        System.out.println("5. Exit");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty.");
        }
    }

    private static void insertStudent() {
        try {
            System.out.println("Insert new student:");

            String name = readNonEmptyString("Name: ");
            String regNum = readNonEmptyString("Registration Number: ");
            String course = readNonEmptyString("Course: ");
            int age = readInt("Age: ");

            if (age < 0) {
                System.out.println("Age cannot be negative.");
                return;
            }

            Student student = new Student(name, regNum, course, age);
            dao.insertStudent(student);
            System.out.println("Student inserted successfully with ID: " + student.getId());

        } catch (Exception e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    private static void listStudents() {
        try {
            List<Student> students = dao.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No students found.");
            } else {
                System.out.println("List of students:");
                for (Student s : students) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }

    private static void updateStudent() {
        try {
            int id = readInt("Enter student ID to update: ");
            Student student = dao.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            System.out.println("Current info: " + student);

            String name = readNonEmptyString("New name (leave blank to keep current): ");
            if (!name.isEmpty()) student.setName(name);

            String regNum = readNonEmptyString("New registration number (leave blank to keep current): ");
            if (!regNum.isEmpty()) student.setRegistrationNumber(regNum);

            String course = readNonEmptyString("New course (leave blank to keep current): ");
            if (!course.isEmpty()) student.setCourse(course);

            System.out.print("New age (leave blank to keep current): ");
            String ageInput = scanner.nextLine().trim();
            if (!ageInput.isEmpty()) {
                int age = Integer.parseInt(ageInput);
                if (age < 0) {
                    System.out.println("Age cannot be negative.");
                    return;
                }
                student.setAge(age);
            }

            dao.updateStudent(student);
            System.out.println("Student updated successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number input.");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        try {
            int id = readInt("Enter student ID to delete: ");
            Student student = dao.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            dao.deleteStudent(id);
            System.out.println("Student deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}
