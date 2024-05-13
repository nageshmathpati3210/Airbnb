package com.basic.airbnb.controller;

import com.basic.airbnb.entity.Favourite;
import com.basic.airbnb.entity.Property;
import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.repository.FavouriteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController
{
    private FavouriteRepository favouriteRepository;

    public FavouriteController(FavouriteRepository favouriteRepository)
    {
        this.favouriteRepository = favouriteRepository;
    }

    @PostMapping
    public ResponseEntity<Object> createFavourie(@RequestBody Favourite favourite, @AuthenticationPrincipal PropertyUser user)
    {
        favourite.setPropertyUser(user);
        var save = favouriteRepository.save(favourite);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateFavourite(@RequestBody Favourite favourite,@AuthenticationPrincipal PropertyUser user)
    {

        Favourite byPropertyAndPropertyUser = favouriteRepository.findByPropertyAndPropertyUser(favourite.getProperty(), user);
        if(byPropertyAndPropertyUser!=null)
        {
            byPropertyAndPropertyUser.setIsFavourite(favourite.getIsFavourite());
            Favourite save = favouriteRepository.save(byPropertyAndPropertyUser);
            return new ResponseEntity<>(save,HttpStatus.OK);
        }
        favourite.setPropertyUser(user);
        Favourite save = favouriteRepository.save(favourite);
        return new ResponseEntity<>(save,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Favourite>> getAllFavourite(@AuthenticationPrincipal PropertyUser user)
    {
        List<Favourite> byPropertyUserId = favouriteRepository.findByPropertyUser_Id(user.getId());
        return new ResponseEntity<>(byPropertyUserId,HttpStatus.OK);
    }


}
