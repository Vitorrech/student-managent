package model;

public class Student {
    private int id;
    private String name;
    private String registrationNumber;
    private String course;
    private int age;

    // Constructor without ID (for insertions)
    public Student(String name, String registrationNumber, String course, int age) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.course = course;
        this.age = age;
    }

    // Constructor with ID (for retrievals)
    public Student(int id, String name, String registrationNumber, String course, int age) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.course = course;
        this.age = age;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Override toString for readable printouts
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", course='" + course + '\'' +
                ", age=" + age +
                '}';
    }
}
