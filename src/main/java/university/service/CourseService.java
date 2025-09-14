package university.service;

import university.entity.Course;
import university.entity.Professor;
import university.repository.GenericRepository;

import java.util.List;

public class CourseService {

    private GenericRepository<Course> courseRepo = new GenericRepository<>(Course.class);
    private GenericRepository<Professor> professorRepo = new GenericRepository<>(Professor.class);

    public Course create(String name, int credits, long professorId) {
        // Find professor by id
        Professor professor = professorRepo.read(professorId);
        if (professor == null) {
            throw new IllegalArgumentException("Professor with id " + professorId + " not found");
        }

        // Create course and link with professor
        Course course = new Course();
        course.setName(name);
        course.setCredits(credits);
        course.setProfessor(professor);

        return courseRepo.create(course);
    }

    public List<Course> list() {
        return courseRepo.findAll();
    }
}
