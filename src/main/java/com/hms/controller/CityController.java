package com.hms.controller;

import com.hms.Service.CityService;
import com.hms.payload.CityDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService citySer;

    public CityController(CityService citySer) {
        this.citySer = citySer;
    }

    @PostMapping("/addCity")
    public ResponseEntity<?> addCity(
            @RequestBody CityDto cityDto
    ){
        CityDto dto = citySer.addCity(cityDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
