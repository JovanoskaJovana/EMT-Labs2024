package mk.ukim.finki.lab1bookstorefinal.service.impl;

import mk.ukim.finki.lab1bookstorefinal.model.Author;
import mk.ukim.finki.lab1bookstorefinal.model.Book;
import mk.ukim.finki.lab1bookstorefinal.model.dto.BookDto;
import mk.ukim.finki.lab1bookstorefinal.model.enumerations.BookCategory;
import mk.ukim.finki.lab1bookstorefinal.model.events.BookChangedEvent;
import mk.ukim.finki.lab1bookstorefinal.model.events.BookCreatedEvent;
import mk.ukim.finki.lab1bookstorefinal.model.events.BookDeletedEvent;
import mk.ukim.finki.lab1bookstorefinal.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.lab1bookstorefinal.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab1bookstorefinal.repository.AuthorRepository;
import mk.ukim.finki.lab1bookstorefinal.repository.BookRepository;
import mk.ukim.finki.lab1bookstorefinal.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private  final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor().getId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor().getId()));
        Book book = new Book(bookDto.getName(), bookDto.getBookCategory(), author, bookDto.getAvailableCopies());
        bookRepository.save(book);
        applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Book create(String name, BookCategory category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);
        bookRepository.save(book);
        applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return book;
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor().getId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor().getId()));
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(book.getName());
        book.setBookCategory(bookDto.getBookCategory());
        book.setAuthor(author);
        book.setAvailableCopies(book.getAvailableCopies());

        bookRepository.save(book);
        applicationEventPublisher.publishEvent(new BookChangedEvent(book));
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.deleteById(id);
        applicationEventPublisher.publishEvent(new BookDeletedEvent(book));
    }

    @Override
    public boolean markAsRented(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        int copies = book.getAvailableCopies();

        if (copies > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            return  true;
        }
        return false;
    }
}
