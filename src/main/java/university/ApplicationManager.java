package university;

import university.entity.*;
import university.repository.GenericRepository;
import university.service.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ApplicationManager {

    private StudentService studentService;
    private ProfessorService professorService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;
    private DepartmentService departmentService;

    public ApplicationManager() {
        studentService = new StudentService();
        professorService = new ProfessorService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService();
        departmentService = new DepartmentService();
    }

    public void registerStudent() {
        System.out.println("Please add the required data!");
        String name = Helper.getStringFromUser("Name");
        String email = Helper.getStringFromUser("Email");
        int year = Helper.getIntFromUser("Year");

        studentService.register(name,email,year);
    }

    public void updateStudent() {
        listStudents();
        Long id = Helper.getLongFromUser("Enter the ID of the student to update");
        Student student = studentService.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        String name = Helper.getStringFromUser("Enter new name");
        String email = Helper.getStringFromUser("Enter new email");
        int year = Helper.getIntFromUser("Enter new enrollment year");

        if (!name.isEmpty()) student.setName(name);
        if (!email.isEmpty()) student.setEmail(email);
        student.setEnrollmentYear(year);

        studentService.update(student);
        System.out.println("Student updated successfully!");
    }

    public void deleteStudent() {
        listStudents();
        Long id = Helper.getLongFromUser("Enter the ID of the student to delete");
        Student student = studentService.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        studentService.delete(student);
        System.out.println("Student deleted successfully!");
    }

    public void listStudents() {
        System.out.println("Students in the system:");
        studentService.list().forEach(System.out::println);
    }

    public void addProfessor() {
        System.out.println("Please add the required data!");
        String name = Helper.getStringFromUser("Name");
        String email = Helper.getStringFromUser("Email");
        String department = Helper.getStringFromUser("Department");

        professorService.register(name,email,department);
    }

    public void listProfessors() {
        System.out.println("professors in the system:");
        professorService.list().forEach(System.out::println);
    }

    public void createCourse() {
        System.out.println("Please add the required data!");
        String name = Helper.getStringFromUser("Name");
        int credits = Helper.getIntFromUser("Credits");
        System.out.println("Choose a professor from the list");
        listProfessors();
        int professorId = Helper.getIntFromUser("ProfessorId");

        courseService.create(name,credits,professorId);
    }

    public void listCourses() {
        System.out.println("courses in the system:");
        courseService.list().forEach(System.out::println);
    }

    public void enrollStudent() {
        System.out.println("Please add the required data!");
        System.out.println("Choose Students from the list: ");
        listStudents();
        long studentId = Helper.getLongFromUser("Student ID");
        System.out.println("Choose Courses from the list: ");
        listCourses();
        long courseId = Helper.getLongFromUser("Course ID");
        String grade = Helper.getStringFromUser("Grade");
        enrollmentService.enrollStudent(studentId,courseId,grade);
    }

    public void listStudentsInCourseSelected() {
        System.out.println("Choose the course id from the list below");
        listCourses();
        long courseId = Helper.getLongFromUser("Course ID");

        List<Student> students = enrollmentService.listStudentsInCourse(courseId);

        if (students.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            System.out.println("Students enrolled in the course:");
            students.forEach(System.out::println);
        }
    }


    public void listCoursesForStudentSelected() {
        System.out.println("Choose the Student ID from the list below");
        listStudents();
        long studentId = Helper.getLongFromUser("Student ID");

        List<Course> courses = enrollmentService.listCoursesForStudentSelected(studentId);

        if (courses.isEmpty()) {
            System.out.println("This student is not enrolled in any courses.");
        } else {
            System.out.println("Courses for the student:");
            courses.forEach(System.out::println);
        }
    }


    public void createDepartment() {
        System.out.println("Please add the required data!");
        String name = Helper.getStringFromUser("Name");
        String building = Helper.getStringFromUser("Building");
        departmentService.create(name,building);
    }

    public void listDepartments(){
        System.out.println("courses in the system:");
        departmentService.list().forEach(System.out::println);
    }
}
