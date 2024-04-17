package mk.ukim.finki.lab1bookstorefinal.model.dto;

import lombok.Data;
import mk.ukim.finki.lab1bookstorefinal.model.enumerations.BookCategory;

@Data
public class BookDto {

    private String name;
    private BookCategory bookCategory;
    private Long authorId;
    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, BookCategory bookCategory, Long authorId, Integer availableCopies) {
        this.name = name;
        this.bookCategory = bookCategory;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }


}
