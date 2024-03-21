package mk.ukim.finki.lab1bookstorefinal.service;


import mk.ukim.finki.lab1bookstorefinal.model.Author;
import mk.ukim.finki.lab1bookstorefinal.model.dto.AuthorDto;


import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    Author create(String name, String surname, Long countryId);
    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);
}
