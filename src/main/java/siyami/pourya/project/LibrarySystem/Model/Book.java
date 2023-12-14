package siyami.pourya.project.LibrarySystem.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Year;

@Getter
@Setter
@Entity
public class Book {
    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private LocalDate releaseDate;
    private String author;
    private String translator;
    private Year releaseYear;
    private String edition;
    private boolean isBorrowed;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member borrowedBy;
}
