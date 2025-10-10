package university.service;

import university.entity.Course;
import university.entity.Professor;
import university.repository.GenericRepository;

import java.util.List;

public class CourseService {

    private final GenericRepository<Course> courseRepo = new GenericRepository<>(Course.class);
    private final GenericRepository<Professor> professorRepo = new GenericRepository<>(Professor.class);

    /**
     * Creates a new course and assigns a professor.
     * Prevents assigning the same professor to the same course name if desired.
     */
    public Course create(String name, int credits, long professorId) {
        Professor professor = professorRepo.read(professorId);
        if (professor == null) {
            throw new IllegalArgumentException("Professor with ID " + professorId + " not found.");
        }

        // Optional: prevent same professor teaching a course with the same name
        List<Course> existingCourses = courseRepo.findAll();
        for (Course c : existingCourses) {
            if (c.getProfessor().getId().equals(professorId) && c.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Professor " + professor.getName() + " is already teaching this course.");
            }
        }

        Course course = new Course();
        course.setName(name);
        course.setCredits(credits);
        course.setProfessor(professor);
        if (course.getEnrollments() == null) course.setEnrollments(new java.util.ArrayList<>());

        return courseRepo.create(course);
    }
    public List<Course> list() {
        return courseRepo.findAll();
    }
}
