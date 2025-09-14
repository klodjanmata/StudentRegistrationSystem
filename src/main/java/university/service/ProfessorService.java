package university.service;
import university.entity.Professor;
import university.repository.GenericRepository;
import java.util.List;

public class ProfessorService {

    private GenericRepository<Professor> professorRepo = new GenericRepository<>(Professor.class);

    public Professor register(String name, String email, String department) {
        Professor professor = new Professor();
        professor.setName(name);
        professor.setEmail(email);
        professor.setDepartment(department);
        return professorRepo.create(professor);
    }

    public List<Professor> list() {
        return professorRepo.findAll();
    }

}
