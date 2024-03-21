package mk.ukim.finki.lab1bookstorefinal.web;

import mk.ukim.finki.lab1bookstorefinal.model.Author;
import mk.ukim.finki.lab1bookstorefinal.model.Country;
import mk.ukim.finki.lab1bookstorefinal.model.dto.AuthorDto;
import mk.ukim.finki.lab1bookstorefinal.model.dto.CountryDto;
import mk.ukim.finki.lab1bookstorefinal.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return countryService.listAll();
    }

    @PostMapping("/addCountry")
    public ResponseEntity<Country> saveCountry (@RequestBody CountryDto countryDto) {
        return countryService.save(countryDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/editCountry/{id}")
    public ResponseEntity<Country> editAuthor (@PathVariable Long id,
                                              @RequestBody CountryDto countryDto) {
        return countryService.edit(id, countryDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/deleteCountry/{id}")
    public ResponseEntity<Country> deleteCountryById (@PathVariable Long id) {
        countryService.deleteById(id);
        if (countryService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
