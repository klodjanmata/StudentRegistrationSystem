package university.entity;
import jakarta.persistence.*;
import java.util.*;

    @Entity
    @Table(name = "students")
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(name = "enrollment_year")
        private int enrollmentYear;

        @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
        private List<Enrollment> enrollments = new ArrayList<>();

}
