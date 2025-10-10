package university.service;

import university.entity.Department;
import university.repository.GenericRepository;

import java.util.List;

public class DepartmentService {

    private final GenericRepository<Department> departmentRepo = new GenericRepository<>(Department.class);

    /**
     * Creates a new department.
     * Prevents duplicate names.
     */
    public Department create(String name, String building) {
        // Check for duplicate department name
        List<Department> existing = departmentRepo.findAll();
        boolean exists = existing.stream()
                .anyMatch(d -> d.getName().equalsIgnoreCase(name));
        if (exists) {
            throw new IllegalArgumentException("A department with the name '" + name + "' already exists.");
        }

        Department department = new Department();
        department.setName(name);
        department.setBuilding(building);
        return departmentRepo.create(department);
    }

    /*List all departments*/
    public List<Department> list() {
        return departmentRepo.findAll();
    }

    /*Get department by ID*/
    public Department getDepartmentById(Long id) {
        return departmentRepo.read(id);
    }

    /*Update a department*/
    public Department update(Department department) {
        return departmentRepo.update(department);
    }

    /*Delete a department*/
    public void delete(Department department) {
        departmentRepo.delete(department);
    }
}
