package com.example.springdatajpa_test01.student;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor // generates an all-args constructor
@NoArgsConstructor // generates a no-args constructor
@Getter
@Setter
@Entity(name = "Student")
@Table(name = "student",
uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique" , columnNames = "email")
})
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",
    sequenceName = "student_sequence",  allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "age")
    private Integer age;
    private LocalDate date;
}
