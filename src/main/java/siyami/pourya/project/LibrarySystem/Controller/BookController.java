package siyami.pourya.project.LibrarySystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siyami.pourya.project.LibrarySystem.Model.Book;
import siyami.pourya.project.LibrarySystem.Service.BookService;
import siyami.pourya.project.LibrarySystem.Util.LibrarySystemExcelExport;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/getBooksByName")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String name) {
        return new ResponseEntity<>(bookService.findByTitle(name), HttpStatus.OK);
    }

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam String name) {
        return new ResponseEntity<>(bookService.findByAuthor(name), HttpStatus.OK);
    }

    @GetMapping("/getBooksByTranslator")
    public ResponseEntity<List<Book>> findByTranslator(@RequestParam String name) {
        return new ResponseEntity<>(bookService.findByTranslator(name), HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/updateBook")
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        Book borrowedBook = bookService.borrowBook(bookId, userId);
        if (borrowedBook != null) {
            return ResponseEntity.ok(borrowedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);
        if (returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/excelExport")
    public ModelAndView exportToExcel() {
        ModelAndView mav = new ModelAndView();
        mav.setView(new LibrarySystemExcelExport());
        List<Book> list = bookService.findAll();
        mav.addObject("list", list);
        return mav;
    }
}
