package university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "credits")
    private int credits;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public String toString() {
        return String.format(
                "[%-3d] %-30s | Credits: %-2d | Professor: %s",
                id, name, credits, professor != null ? professor.getName() : "N/A"
        );
    }
}
