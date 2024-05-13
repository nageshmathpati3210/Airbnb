package com.basic.airbnb.controller;


import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.payload.LoinDto;
import com.basic.airbnb.payload.PropertyUserDto;
import com.basic.airbnb.payload.TokenResponse;
import com.basic.airbnb.service.PropertyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class PropertyUserController
{

    @Autowired
    private PropertyUserService propertyUserService;


    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody PropertyUserDto propertyUserDto)
    {
        PropertyUser propertyUser = propertyUserService.addUser(propertyUserDto);
        if(propertyUserDto!=null)
        {
            return new ResponseEntity<>("Registration is sucessfull", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoinDto loginDto)
    {

        String s = propertyUserService.verifyLogin(loginDto);
        if(s != null)
        {
            TokenResponse t=new TokenResponse();
            t.setToken(s);
            return new ResponseEntity<>(t,HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid credentials!",HttpStatus.UNAUTHORIZED);


    }


    @GetMapping("/profile")
    public PropertyUser getProperty(@AuthenticationPrincipal PropertyUser user)
    {
        return user;
    }




}
