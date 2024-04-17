package mk.ukim.finki.lab1bookstorefinal.web;

import mk.ukim.finki.lab1bookstorefinal.model.Book;
import mk.ukim.finki.lab1bookstorefinal.model.dto.BookDto;
import mk.ukim.finki.lab1bookstorefinal.model.enumerations.BookCategory;
import mk.ukim.finki.lab1bookstorefinal.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/categories")
    public List<BookCategory> findAllCategories() {
        return Arrays.stream(BookCategory.values()).toList();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/books/add")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto) {
        System.out.println(bookDto);
        return bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/books/edit/{id}")
    public ResponseEntity<Book> editBook (@PathVariable Long id,
                                          @RequestBody BookDto bookDto) {
        return bookService.edit(id, bookDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity<Book> deleteBookById (@PathVariable Long id) {
        bookService.deleteById(id);
        if (bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/books/rent/{id}")
    public ResponseEntity<Book> rentABook (@PathVariable Long id) {
        boolean rentSuccess = bookService.markAsRented(id);
        if (rentSuccess) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
