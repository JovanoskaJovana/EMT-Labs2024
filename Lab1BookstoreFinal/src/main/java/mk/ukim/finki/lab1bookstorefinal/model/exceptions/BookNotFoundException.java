package mk.ukim.finki.lab1bookstorefinal.model.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super(String.format("Book with id: %d is not found", id));
    }
}
