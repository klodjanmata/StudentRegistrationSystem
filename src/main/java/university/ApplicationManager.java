package university;

import university.entity.*;
import university.service.*;

import java.util.Arrays;
import java.util.List;

public class ApplicationManager {

    private final StudentService studentService;
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final DepartmentService departmentService;

    public ApplicationManager() {
        studentService = new StudentService();
        professorService = new ProfessorService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService();
        departmentService = new DepartmentService();
    }

    // ---------------------- STUDENTS ----------------------
    public void registerStudent() {
        try {
            System.out.println("Please add the required data!");
            String name = Helper.getStringFromUser("Name");
            String email = Helper.getStringFromUser("Email");
            int year = Helper.getIntFromUser("Enrollment Year");

            studentService.register(name, email, year);
            System.out.println("Student registered successfully!");

        } catch (Exception e) {
            System.err.println("Error registering student: " + e.getMessage());
        }
    }

    public void updateStudent() {
        try {
            listStudents();
            long id = Helper.getLongFromUser("Enter the ID of the student to update");
            Student student = studentService.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found!");
                return;
            }

            String name = Helper.getStringFromUser("Enter new name (leave blank to keep current)");
            String email = Helper.getStringFromUser("Enter new email (leave blank to keep current)");
            int year = Helper.getIntFromUser("Enter new enrollment year");

            if (!name.isEmpty()) student.setName(name);
            if (!email.isEmpty()) student.setEmail(email);
            student.setEnrollmentYear(year);

            studentService.update(student);
            System.out.println("Student updated successfully!");

        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
    }

    public void deleteStudent() {
        try {
            listStudents();
            long id = Helper.getLongFromUser("Enter the ID of the student to delete");
            Student student = studentService.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found!");
                return;
            }

            studentService.delete(student);
            System.out.println("Student deleted successfully!");

        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    public void listStudents() {
        System.out.println("\n--- Students in the System ---");
        System.out.printf("%-5s %-20s %-25s %-10s%n", "ID", "Name", "Email", "Year");
        System.out.println("-------------------------------------------------------------");
        List<Student> students = studentService.list();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }


    // ---------------------- PROFESSORS ----------------------
    public void addProfessor() {
        try {
            System.out.println("Please add the required data!");
            String name = Helper.getStringFromUser("Name");
            String email = Helper.getStringFromUser("Email");

            // List available departments
            System.out.println("Choose a department from the list:");
            List<Department> departments = departmentService.list();
            if (departments.isEmpty()) {
                System.out.println("No departments available. Please create a department first.");
                return;
            }
            departments.forEach(d -> System.out.println(d.getId() + ". " + d.getName()));

            long departmentId = Helper.getLongFromUser("Department ID");
            Department department = departments.stream()
                    .filter(d -> d.getId().equals(departmentId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));

            Professor professor = professorService.register(name, email, department);
            System.out.println("Professor added successfully: " + professor.getName());

        } catch (Exception e) {
            System.out.println("Error adding professor: " + e.getMessage());
        }
    }


    public void listProfessors() {
        System.out.println("\n--- Professors in the System ---");
        System.out.printf("%-5s %-25s %-25s %-20s%n", "ID", "Name", "Email", "Department");
        System.out.println("--------------------------------------------------------------------");

        List<Professor> professors = professorService.list();
        if (professors.isEmpty()) {
            System.out.println("No professors found.");
        } else {
            professors.forEach(p -> System.out.printf(
                    "%-5d %-25s %-25s %-20s%n",
                    p.getId(), p.getName(), p.getEmail(), p.getDepartment().getName()
            ));
        }
    }


    // ---------------------- COURSES ----------------------
    public void createCourse() {
        try {
            System.out.println("Please add the required data!");
            String name = Helper.getStringFromUser("Course Name");
            int credits = Helper.getIntFromUser("Credits");

            System.out.println("Choose a professor from the list:");
            listProfessors();
            long professorId = Helper.getLongFromUser("Professor ID");

            courseService.create(name, credits, professorId);
            System.out.println("Course created successfully!");

        } catch (Exception e) {
            System.err.println("Error creating course: " + e.getMessage());
        }
    }

    public void listCourses() {
        System.out.println("\n--- Courses in the System ---");
        System.out.printf("%-5s %-25s %-8s %-25s%n", "ID", "Name", "Credits", "Professor");
        System.out.println("-----------------------------------------------------------------");

        List<Course> courses = courseService.list();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(c -> System.out.printf(
                    "%-5d %-25s %-8d %-25s%n",
                    c.getId(), c.getName(), c.getCredits(), c.getProfessor().getName()
            ));
        }
    }


    // ---------------------- ENROLLMENTS ----------------------
    public void enrollStudent() {
        try {
            System.out.println("Enroll a student in a course.");

            // Check students first
            System.out.println("Choose a student:");
            List<Student> students = studentService.list();
            if (students.isEmpty()) {
                System.out.println("⚠️  No students available. Please register a student first.");
                return;
            }
            students.forEach(System.out::println);
            long studentId = Helper.getLongFromUser("Student ID");

            // Check courses next
            System.out.println("Choose a course:");
            List<Course> courses = courseService.list();
            if (courses.isEmpty()) {
                System.out.println("⚠️  No courses available. Please create a course first.");
                return;
            }
            courses.forEach(System.out::println);
            long courseId = Helper.getLongFromUser("Course ID");

            // Grade selection
            String gradeInput = Helper.getStringFromUser("Grade (A/B/C/D/F/INCOMPLETE)").toUpperCase();
            List<String> validGrades = java.util.Arrays.asList("A","B","C","D","F","INCOMPLETE");
            if (!validGrades.contains(gradeInput)) {
                System.out.println("Invalid grade, defaulting to INCOMPLETE");
                gradeInput = "INCOMPLETE";
            }

            enrollmentService.enrollStudent(studentId, courseId, gradeInput);
            System.out.println("✅ Student enrolled successfully!");

        } catch (Exception e) {
            System.err.println("❌ Error enrolling student: " + e.getMessage());
        }
    }


    public void listStudentsInCourseSelected() {
        try {
            System.out.println("Choose the course:");
            List<Course> courses = courseService.list(); // or listCourses() returning List<Course>

            if (courses.isEmpty()) {
                System.out.println("No courses found. Please add courses first.");
                return; // go back to the previous menu
            }

            // Display courses
            courses.forEach(c -> System.out.printf("[%d] %s\n", c.getId(), c.getName()));

            // Ask for course ID only if courses exist
            long courseId = Helper.getLongFromUser("Course ID");

            List<Student> students = enrollmentService.listStudentsInCourse(courseId);
            if (students.isEmpty()) {
                System.out.println("No students enrolled in this course.");
            } else {
                System.out.println("Students enrolled:");
                students.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.err.println("❌ Error listing students in course: " + e.getMessage());
        }
    }

    public void listCoursesForStudentSelected() {
        try {
            System.out.println("Choose a student:");
            listStudents();
            long studentId = Helper.getLongFromUser("Student ID");

            List<Course> courses = enrollmentService.listCoursesForStudent(studentId);
            if (courses.isEmpty()) {
                System.out.println("This student is not enrolled in any courses.");
            } else {
                System.out.println("Courses for the student:");
                courses.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.err.println("❌ Error listing courses for student: " + e.getMessage());
        }
    }

    // ---------------------- DEPARTMENTS ----------------------
    public void createDepartment() {
        System.out.println("Please add the required data!");
        String name = Helper.getStringFromUser("Name");
        String building = Helper.getStringFromUser("Building");

        try {
            Department department = departmentService.create(name, building); // return type Department
            System.out.println("Department created successfully: " + department.getName());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }


    public void listDepartments() {
        System.out.println("\n--- Departments in the System ---");
        System.out.printf("%-5s %-25s %-20s%n", "ID", "Name", "Building");
        System.out.println("-----------------------------------------------------");

        List<Department> departments = departmentService.list();
        if (departments.isEmpty()) {
            System.out.println("No departments found.");
        } else {
            departments.forEach(d -> System.out.printf(
                    "%-5d %-25s %-20s%n",
                    d.getId(), d.getName(), d.getBuilding()
            ));
        }
    }

}
