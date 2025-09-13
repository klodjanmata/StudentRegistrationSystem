package university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Departament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "building")
    private String building;

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}
