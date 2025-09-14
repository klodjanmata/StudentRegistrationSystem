package university.service;

import university.entity.Department;
import university.repository.GenericRepository;

import java.util.List;

public class DepartmentService {

    private GenericRepository<Department> departmentRepo = new GenericRepository<>(Department.class);

    public Department create(String name, String building) {
        Department department = new Department();
        department.setName(name);
        department.setBuilding(building);
        return departmentRepo.create(department);
    }

    public List<Department> list() {
        return departmentRepo.findAll();
    }
}
