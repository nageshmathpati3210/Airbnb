package com.basic.airbnb.repository;

import com.basic.airbnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long>
{

    List<Country> findByCountryName(String countryName);
}