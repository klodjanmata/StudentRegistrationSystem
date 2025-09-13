package university.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Departament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "building")
    private String building;
}
