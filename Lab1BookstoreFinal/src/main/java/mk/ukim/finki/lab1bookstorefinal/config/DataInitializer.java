package mk.ukim.finki.lab1bookstorefinal.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1bookstorefinal.model.enumerations.BookCategory;
import mk.ukim.finki.lab1bookstorefinal.service.AuthorService;
import mk.ukim.finki.lab1bookstorefinal.service.BookService;
import mk.ukim.finki.lab1bookstorefinal.service.CountryService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookService bookService;

    private final AuthorService authorService;
    private final CountryService countryService;


    public DataInitializer(BookService bookService, AuthorService authorService, CountryService create, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData() {

        BookCategory[] categories = BookCategory.values();
        for (int i = 1; i < 6; i++){
            countryService.create("Country " + i, "Continent " + i);

            authorService.create("AuthorName " + i, "AuthorSurname" + i, (long) ((i - 1) % 5 + 1));

            bookService.create("Book " + i, categories[(i - 1) % categories.length], (long) ((i - 1) % 5 + 1), 5 + i);

        }
    }
}
