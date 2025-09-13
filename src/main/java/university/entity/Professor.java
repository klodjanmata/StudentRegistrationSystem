package university.entity;
import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name = "professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses = new ArrayList<>();
}
