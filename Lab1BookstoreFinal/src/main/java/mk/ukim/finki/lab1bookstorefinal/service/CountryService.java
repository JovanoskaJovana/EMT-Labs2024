package mk.ukim.finki.lab1bookstorefinal.service;


import mk.ukim.finki.lab1bookstorefinal.model.Country;
import mk.ukim.finki.lab1bookstorefinal.model.dto.BookDto;
import mk.ukim.finki.lab1bookstorefinal.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Country create(String name, String continent);
    Optional<Country> edit(Long id, CountryDto countryDto);

    void deleteById(Long id);
}
