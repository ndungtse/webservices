package rw.ac.rca.webservices.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rw.ac.rca.webservices.utils.EGender;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studId;
    private String firstName;
    private String lastName;
    private int age;
    private EGender gender;

    public Student() {

    }
}
