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
@Table(name = "professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses = new ArrayList<>();

    @Override
    public String toString() {
        return String.format(
                "[%-3d] %-20s | %-25s | Dept: %s",
                id, name, email, department != null ? department.getName() : "N/A"
        );
    }

}
