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
@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"})
)

public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    public enum Grade {
        A, B, C, D, F, INCOMPLETE
    }


    @Override
    public String toString() {
        return String.format(
                "[%-3d] Student: %-20s | Course: %-30s | Grade: %-10s",
                id,
                student != null ? student.getName() : "N/A",
                course != null ? course.getName() : "N/A",
                grade != null ? grade.toString() : "INCOMPLETE"
        );
    }

}

