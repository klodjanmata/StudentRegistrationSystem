package university;

import university.entity.*;
import university.repository.GenericRepository;
import university.service.CourseService;
import university.service.EnrollmentService;
import university.service.ProfessorService;
import university.service.StudentService;

import java.time.LocalDate;
import java.util.Date;

public class ApplicationManager {

    private StudentService studentService;
    private ProfessorService professorService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;

    public ApplicationManager() {
        studentService = new StudentService();
        professorService = new ProfessorService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService();
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
        System.out.println("Chose Students from the list: ");
        listStudents();
        long studentId = Helper.getLongFromUser("Student ID");
        System.out.println("Chose Courses from the list: ");
        listCourses();
        long courseId = Helper.getLongFromUser("Course ID");
        String grade = Helper.getStringFromUser("Grade");
        enrollmentService.enrollStudent(studentId,courseId,grade);
    }

    public void listStudentsInCourseSelected() {
        enrollmentService.listStudentsInCourse();
    }

    public void listCoursesForStudentSelected() {
        enrollmentService.listCoursesForStudentSelected();
    }
}
