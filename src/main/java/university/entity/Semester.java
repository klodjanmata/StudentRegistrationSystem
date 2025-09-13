package university.entity;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "semesters")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year")
    private int year;
    @Column(name = "term")
    private String term;

    @OneToMany(mappedBy = "semester")
    private List<Enrollment> enrollments;
}
