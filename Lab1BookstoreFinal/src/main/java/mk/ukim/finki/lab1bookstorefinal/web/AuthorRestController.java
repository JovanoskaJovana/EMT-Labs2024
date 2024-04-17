package mk.ukim.finki.lab1bookstorefinal.web;

import mk.ukim.finki.lab1bookstorefinal.model.Author;
import mk.ukim.finki.lab1bookstorefinal.model.dto.AuthorDto;
import mk.ukim.finki.lab1bookstorefinal.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @PostMapping("/authors/addAuthor")
    public ResponseEntity<Author> saveAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/authors/editAuthor/{id}")
    public ResponseEntity<Author> editAuthor (@PathVariable Long id,
                                              @RequestBody AuthorDto authorDto) {
        return authorService.edit(id, authorDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/authors/deleteAuthor/{id}")
    public ResponseEntity<Author> deleteAuthorById (@PathVariable Long id) {
        authorService.deleteById(id);
        if (authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }


}
