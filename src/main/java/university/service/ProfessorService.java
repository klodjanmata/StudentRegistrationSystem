package university.service;

import university.entity.Department;
import university.entity.Professor;
import university.repository.GenericRepository;

import java.util.List;

public class ProfessorService {

    private final GenericRepository<Professor> professorRepo = new GenericRepository<>(Professor.class);

    /**
     * Registers a new professor.
     * Prevents duplicate emails.
     */
    public Professor register(String name, String email, Department department) {
        List<Professor> existing = professorRepo.findAll();
        boolean exists = existing.stream()
                .anyMatch(p -> p.getEmail().equalsIgnoreCase(email));
        if (exists) {
            throw new IllegalArgumentException("A professor with email " + email + " already exists.");
        }

        Professor professor = new Professor();
        professor.setName(name);
        professor.setEmail(email);
        professor.setDepartment(department); // pass Department object
        return professorRepo.create(professor);
    }


    /*List all professors*/
    public List<Professor> list() {
        return professorRepo.findAll();
    }

    /*Get professor by ID*/
    public Professor getProfessorById(Long id) {
        return professorRepo.read(id);
    }

    /*Update a professor*/
    public Professor update(Professor professor) {
        return professorRepo.update(professor);
    }

    /*Delete a professor*/
    public void delete(Professor professor) {
        professorRepo.delete(professor);
    }
}
