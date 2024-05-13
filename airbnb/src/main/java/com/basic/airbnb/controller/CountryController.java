package com.basic.airbnb.controller;


import com.basic.airbnb.entity.Country;
import com.basic.airbnb.repository.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController
{

    private CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    @GetMapping
    public ResponseEntity<?> getAllLocationsByCountry(@RequestBody Country country)
    {
        List<Country> byCountryName = countryRepository.findByCountryName(country.getCountryName());
        return new ResponseEntity<>(byCountryName, HttpStatus.OK);

    }






}
