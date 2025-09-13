package university;

import university.entity.*;
import university.repository.GenericRepository;
import university.service.CourseService;
import university.service.EnrollmentService;
import university.service.ProfessorService;
import university.service.StudentService;

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

    }

    public void updateStudent() {

    }

    public void deleteStudent() {

    }

    public void listStudents() {

    }

    public void addProfessor() {

    }

    public void listProfessors() {

    }

    public void createCourse() {

    }

    public void listCourses() {

    }

    public void enrollStudent() {

    }

    public void listStudentsInCourseSelected() {

    }

    public void listCoursesForStudentSelected() {

    }
}
