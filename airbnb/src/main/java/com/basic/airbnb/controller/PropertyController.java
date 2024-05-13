package com.basic.airbnb.controller;


import com.basic.airbnb.entity.Property;
import com.basic.airbnb.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/v1/property")
@RestController
public class PropertyController
{

    private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @GetMapping("/{locationName}")
    public ResponseEntity<?> getAllpropertyByLocation(@PathVariable String locationName)
    {
        List<Property> properties = propertyRepository.findPropertyByLocation(locationName);
        System.out.println(Arrays.asList(properties));
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }


}
