package mk.ukim.finki.lab1bookstorefinal.service;

import mk.ukim.finki.lab1bookstorefinal.model.Author;
import mk.ukim.finki.lab1bookstorefinal.model.Book;
import mk.ukim.finki.lab1bookstorefinal.model.dto.BookDto;
import mk.ukim.finki.lab1bookstorefinal.model.enumerations.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto bookDto);

    Book create(String name, BookCategory category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    boolean markAsRented(Long id);

}
