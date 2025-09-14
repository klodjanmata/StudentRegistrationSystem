package university.service;
import university.entity.Course;
import university.repository.GenericRepository;
import java.util.List;

public class CourseService {

    private GenericRepository<Course> courseRepo = new GenericRepository<>(Course.class);

    public Course create(String name, int credits, int professorId) {
        Course course = new Course();
        course.setName(name);
        course.setCredits(credits);
        course.setProfessorId(professorId);
        return courseRepo.create(course);
    }

    public List<Course> list() {
        return courseRepo.findAll();
    }

}
