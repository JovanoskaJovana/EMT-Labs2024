package mk.ukim.finki.lab1bookstorefinal.model.dto;

import lombok.Data;
import mk.ukim.finki.lab1bookstorefinal.model.Country;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private Country country;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
