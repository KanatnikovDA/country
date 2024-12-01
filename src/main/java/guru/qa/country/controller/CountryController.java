package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping("/")
    public List<Country> getAll() {
        return countryService.getCountries();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCountry(@RequestBody Country country) {
        return ResponseEntity.ok(countryService.addCountry(country));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCountryName(@PathVariable UUID id, @RequestParam String newName) {
        return ResponseEntity.ok(countryService.updateCountryName(id, newName));
    }
}
