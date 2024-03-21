package mk.ukim.finki.lab1bookstorefinal.model.events;

import lombok.Getter;
import mk.ukim.finki.lab1bookstorefinal.model.Book;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class BookDeletedEvent extends ApplicationEvent {

    private LocalDateTime when;
    public BookDeletedEvent(Book source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public BookDeletedEvent(Book source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
