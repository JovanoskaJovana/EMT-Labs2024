package mk.ukim.finki.lab1bookstorefinal.repository;

import mk.ukim.finki.lab1bookstorefinal.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
