package main.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @Column(nullable=false)
    private Integer credits;

    @ManyToMany(mappedBy = "course")
    private List<Student> students=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="professor_id")
    private Professor professor;

    private LocalDate createdAt;
}
