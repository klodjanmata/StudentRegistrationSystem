package university.entity;
import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name = "professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;

    @Column(unique = true, nullable = false,name = "email")
    private String email;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses;
}
