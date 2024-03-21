package mk.ukim.finki.lab1bookstorefinal.repository;

import mk.ukim.finki.lab1bookstorefinal.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
