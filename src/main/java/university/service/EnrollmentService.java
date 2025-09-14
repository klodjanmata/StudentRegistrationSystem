package university.service;
import university.entity.Course;
import university.entity.Enrollment;
import university.entity.Professor;
import university.entity.Student;
import university.repository.GenericRepository;
import java.util.List;

public class EnrollmentService {

    private GenericRepository<Enrollment> enrollmentRepo = new GenericRepository<>(Enrollment.class);
    private GenericRepository<Student> studentRepo = new GenericRepository<>(Student.class);
    private GenericRepository<Course> courseRepo = new GenericRepository<>(Course.class);

    public Enrollment enrollStudent(long studentId, long courseId, String grade) {
        Enrollment enrollment = new Enrollment();
        Student student = studentRepo.read(studentId);
        Course course = courseRepo.read(courseId);

        if (student == null) {
            throw new IllegalArgumentException("Student with id " + studentId + " not found");
        }

        if (course == null) {
            throw new IllegalArgumentException("Course with id " + courseId + " not found");
        }
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(grade);
        return enrollmentRepo.create(enrollment);
    }

    public List<Enrollment> listStudentsInCourse() {
        return enrollmentRepo.findAll();
    }

    public List<Enrollment> listCoursesForStudentSelected() {
        return enrollmentRepo.findAll();
    }

}
