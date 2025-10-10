package university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "building")
    private String building;

    @OneToMany(mappedBy = "department")
    private List<Professor> professors = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Department [ID=%d, Name=%s, Building=%s]",
                id,
                name != null ? name : "N/A",
                building != null ? building : "N/A"
        );
    }
}
