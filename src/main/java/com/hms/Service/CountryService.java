package com.hms.Service;

import com.hms.entity.Country;
import com.hms.payload.CountryDto;
import com.hms.repository.CountryRepository;
import org.springframework.stereotype.Service;


@Service
public class CountryService {


    private CountryRepository countryRepo;

    public CountryService(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    public CountryDto createCountry(CountryDto countryDto) {
        Country country =mapToeEntity(countryDto);
        Country savedCountry= countryRepo.save(country);

        CountryDto dto =mapToDto(savedCountry);
        return  dto;
    }

    //entity to dto
    CountryDto mapToDto(Country country){
        CountryDto dto =new CountryDto();
        dto.setName(country.getName());
        return dto;
    }

    //dto to entity
    Country mapToeEntity(CountryDto countryDto){
        Country country =new Country();
        country.setName(countryDto.getName());
        return country;
    }
}
