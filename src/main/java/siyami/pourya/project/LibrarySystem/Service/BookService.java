package siyami.pourya.project.LibrarySystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyami.pourya.project.LibrarySystem.Model.Book;
import siyami.pourya.project.LibrarySystem.Model.Member;
import siyami.pourya.project.LibrarySystem.Repositories.BookRepository;
import siyami.pourya.project.LibrarySystem.Repositories.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByAuthor(String name) {
        return bookRepository.findByAuthor(name);
    }

    public List<Book> findByTranslator(String name) {
        return bookRepository.findByTranslator(name);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        Member member = memberRepository.findById(userId).orElse(null);

        if (book != null && !book.isBorrowed() && member != null && member.getBookNum() < 2) {
            book.setBorrowedBy(member);
            book.setBorrowed(true);
            member.setBookNum(member.getBookNum() + 1);
            member.setBorrowBookTime(LocalDate.now());
            return save(book);
        } else if (book == null) {
            throw new RuntimeException("کتاب با آی دی مورد نظر پیدا نشد");
        } else if (member == null) {
            throw new RuntimeException("عضوی با آی دی مورد نظر پیدا نشد");
        } else if (book.isBorrowed()) {
            throw new RuntimeException("این کتاب قبلا به امانت گرفته شده است");
        } else if (member.getBookNum() == 2) {
            throw new RuntimeException("یک کاربر همزمان فقط میتواند 2 کتاب به امانت بگیرد");
        }
        return null;
    }

    public Book returnBook(Long bookId) {
        Book book = findById(bookId);
        if (book != null && book.isBorrowed()) {
            Member member = memberRepository.findById(book.getBorrowedBy().getMemberId()).orElse(null);
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            if (member != null) {
                member.setBookNum(member.getBookNum() - 1);
                member.setReturnBookTime(LocalDate.now());
            }
            return save(book);
        }
        return null;
    }
}
