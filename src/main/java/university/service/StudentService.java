package university.service;

import university.entity.Student;
import university.repository.GenericRepository;

import java.util.List;

public class StudentService {

    private final GenericRepository<Student> studentRepo = new GenericRepository<>(Student.class);

    /**
     * Registers a new student.
     * Checks for duplicate email.
     */
    public Student register(String name, String email, int year) {
        // Check for duplicate email
        List<Student> existing = studentRepo.findAll();
        boolean exists = existing.stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(email));
        if (exists) {
            throw new IllegalArgumentException("A student with email " + email + " already exists.");
        }

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setEnrollmentYear(year);
        return studentRepo.create(student);
    }

    /**
     * Updates a student record
     */
    public Student update(Student student) {
        return studentRepo.update(student);
    }

    /**
     * Deletes a student
     */
    public void delete(Student student) {
        studentRepo.delete(student);
    }

    /**
     * Returns all students
     */
    public List<Student> list() {
        return studentRepo.findAll();
    }

    /**
     * Get student by ID
     */
    public Student getStudentById(Long id) {
        return studentRepo.read(id);
    }
}
