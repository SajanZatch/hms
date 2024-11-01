package com.hms.controller;

import com.hms.Service.CountryService;
import com.hms.payload.CountryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

     private CountryService countrySer;

    public CountryController(CountryService countrySer) {
        this.countrySer = countrySer;
    }

    // http://localhost:8080/api/v1/country
    @PostMapping("/addCountry")
    public String addCountry(){
        return "added";
    }
    @PostMapping("/insertCountry")
    public ResponseEntity<?> createCountry(
            @RequestBody CountryDto countryDto
    ){
        CountryDto dto =countrySer.createCountry(countryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
