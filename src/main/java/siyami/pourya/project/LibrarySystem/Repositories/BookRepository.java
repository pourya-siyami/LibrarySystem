package siyami.pourya.project.LibrarySystem.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import siyami.pourya.project.LibrarySystem.Model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query("update Book b set b.releaseDate=:#{#book?.releaseDate},b.author=:#{#book?.author}," +
            "b.translator=:#{#book?.translator},b.releaseYear=:#{#book?.releaseYear},b.edition=:#{#book?.edition}," +
            "b.isBorrowed=:#{#book?.isBorrowed} where b.bookId=:#{#book?.bookId}")
    void update(@RequestParam("book") Book book);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String name);

    List<Book> findByTranslator(String name);
}
