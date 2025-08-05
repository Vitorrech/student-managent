package dao;

import model.Student;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Insert student into DB
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (name, registrationNumber, course, age) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getRegistrationNumber());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getAge());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));  // set the generated ID to the student object
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error inserting student:");
            System.out.println(e.getMessage());
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("registrationNumber"),
                        rs.getString("course"),
                        rs.getInt("age")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error retrieving students:");
            System.out.println(e.getMessage());
        }

        return students;
    }

    // Get student by ID
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("registrationNumber"),
                        rs.getString("course"),
                        rs.getInt("age")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error retrieving student by ID:");
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Update student
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, registrationNumber = ?, course = ?, age = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getRegistrationNumber());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getAge());
            stmt.setInt(5, student.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("❌ Student with ID " + student.getId() + " not found.");
            } else {
                System.out.println("Student updated successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error updating student:");
            System.out.println(e.getMessage());
        }
    }

    // Delete student by ID
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("❌ Student with ID " + id + " not found.");
            } else {
                System.out.println("Student deleted successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error deleting student:");
            System.out.println(e.getMessage());
        }
    }
}