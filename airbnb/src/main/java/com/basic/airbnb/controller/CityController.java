package com.basic.airbnb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/city")
@RestController
public class CityController
{

    //http://localhost:8080/api/v1/city/create
    @PostMapping("/create")
    public String getCity()
    {
        return "banglore";
    }

}
