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
        String name = Helper.getStringFromUser("Name: ");
        String email = Helper.getStringFromUser("Email: ");
        int year = Helper.getIntFromUser("Date: ");

        studentService.register(name,email,year);
    }

//    public void updateStudent() {
//
//        studentService.update();
//    }
//
//    public void deleteStudent() {
//        studentService.delete();
//    }
//
//    public void listStudents() {
//        studentService.list();
 //   }

//    public void addProfessor() {
//        professorService.add();
//    }

//    public void listProfessors() {
//        professorService.list();
//    }
//
//    public void createCourse() {
//        courseService.create();
//    }
//
//    public void listCourses() {
//        courseService.list();
//    }
//
//    public void enrollStudent() {
//        enrollmentService.enrollStudent();
//    }
//
//    public void listStudentsInCourseSelected() {
//        enrollmentService.listStudentsInCourse();
//    }
//
//    public void listCoursesForStudentSelected() {
//        enrollmentService.listCoursesForStudentSelected();
//    }
}
