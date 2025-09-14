package university.service;
import university.entity.Student;
import university.repository.GenericRepository;

import java.util.List;

public class StudentService {

    private GenericRepository<Student> studentRepo = new GenericRepository<>(Student.class);

    public Student register(String name, String email, int year) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setEnrollmentYear(year);
        return studentRepo.create(student);
    }

    public Student update(Student student) {
        return studentRepo.update(student);
    }

    public void delete(Student student) {
        studentRepo.delete(student);
    }

    public List<Student> list() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepo.read(id);
    }

}
