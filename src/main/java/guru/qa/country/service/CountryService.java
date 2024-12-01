package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.Country;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        return countryRepository.findAll()
                                .stream()
                                .map(Country::fromEntity)
                                .toList();
    }

    @Transactional
    public CountryEntity addCountry(Country country) {
        CountryEntity entity = new CountryEntity();
        entity.setCountryName(country.countryName());
        entity.setCountryCode(country.countryCode());
        return countryRepository.save(entity);
    }

    @Transactional
    public CountryEntity updateCountryName(UUID id, String newName) {
        CountryEntity entity = countryRepository.findById(id)
                                                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
        entity.setCountryName(newName);
        return countryRepository.save(entity);
    }
}
