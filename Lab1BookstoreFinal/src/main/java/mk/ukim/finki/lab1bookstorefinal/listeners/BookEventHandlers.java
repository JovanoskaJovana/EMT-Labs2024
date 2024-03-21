package mk.ukim.finki.lab1bookstorefinal.listeners;

import mk.ukim.finki.lab1bookstorefinal.model.Book;
import mk.ukim.finki.lab1bookstorefinal.model.events.BookChangedEvent;
import mk.ukim.finki.lab1bookstorefinal.model.events.BookCreatedEvent;
import mk.ukim.finki.lab1bookstorefinal.model.events.BookDeletedEvent;
import mk.ukim.finki.lab1bookstorefinal.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab1bookstorefinal.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandlers {

    private final BookService bookService;


    public BookEventHandlers(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated (BookCreatedEvent event) {
        System.out.println("Book Created");
    }

    @EventListener
    public void onBookChanged (BookChangedEvent event) {
        System.out.println("Book Changed");
    }

    @EventListener
    public void onBookDeleted(BookDeletedEvent event) {
        System.out.println("Book deleted");
    }
}
