package university.service;

import university.entity.Course;
import university.entity.Enrollment;
import university.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

import university.repository.GenericRepository;

public class EnrollmentService {

    private final GenericRepository<Enrollment> enrollmentRepo = new GenericRepository<>(Enrollment.class);
    private final GenericRepository<Student> studentRepo = new GenericRepository<>(Student.class);
    private final GenericRepository<Course> courseRepo = new GenericRepository<>(Course.class);

    /**
     * Enrolls a student in a course with an optional grade.
     * Prevents duplicate enrollments.
     */
    public Enrollment enrollStudent(long studentId, long courseId, String gradeStr) {
        Student student = studentRepo.read(studentId);
        Course course = courseRepo.read(courseId);

        if (student == null) throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
        if (course == null) throw new IllegalArgumentException("Course with ID " + courseId + " not found.");

        // Check for duplicate enrollment
        boolean alreadyEnrolled = enrollmentRepo.findAll().stream()
                .anyMatch(e -> e.getStudent().getId().equals(studentId) && e.getCourse().getId().equals(courseId));
        if (alreadyEnrolled) {
            throw new IllegalArgumentException("Student " + student.getName() + " is already enrolled in this course.");
        }

        // Convert string to Grade enum
        Enrollment.Grade grade;
        try {
            grade = Enrollment.Grade.valueOf(gradeStr.toUpperCase());
        } catch (Exception e) {
            grade = Enrollment.Grade.INCOMPLETE; // default if invalid
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(grade);

        return enrollmentRepo.create(enrollment);
    }

    /*Lists all students in a given course*/
    public List<Student> listStudentsInCourse(long courseId) {
        Course course = courseRepo.read(courseId);
        if (course == null) throw new IllegalArgumentException("Course with ID " + courseId + " not found.");

        return enrollmentRepo.findAll().stream()
                .filter(e -> e.getCourse().getId().equals(courseId))
                .map(Enrollment::getStudent)
                .collect(Collectors.toList());
    }

    /* Lists all enrollments for a given course (to access student + grade) */
    public List<Enrollment> listEnrollmentsInCourse(long courseId) {
        Course course = courseRepo.read(courseId);
        if (course == null) throw new IllegalArgumentException("Course with ID " + courseId + " not found.");

        return enrollmentRepo.findAll().stream()
                .filter(e -> e.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
    }

    /* Lists all enrollments for a given student (to access course + grade) */
    public List<Enrollment> listEnrollmentsForStudent(long studentId) {
        Student student = studentRepo.read(studentId);
        if (student == null) throw new IllegalArgumentException("Student with ID " + studentId + " not found.");

        return enrollmentRepo.findAll().stream()
                .filter(e -> e.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }
}

