package siyami.pourya.project.LibrarySystem.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(unique = true)
    private Long nationalCode;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;
    private LocalDate registerDate;
    private String fatherName;
    private String gender;
    private boolean isActive;
    private int bookNum;
    private LocalDate borrowBookTime;
    private LocalDate returnBookTime;
}
