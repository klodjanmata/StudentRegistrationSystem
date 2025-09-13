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

    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", year=" + year +
                ", term='" + term + '\'' +
                ", enrollments=" + enrollments +
                '}';
    }
}
