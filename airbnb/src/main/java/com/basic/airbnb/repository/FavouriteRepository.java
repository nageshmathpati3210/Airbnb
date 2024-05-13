package com.basic.airbnb.repository;

import com.basic.airbnb.entity.Favourite;
import com.basic.airbnb.entity.Property;
import com.basic.airbnb.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long>
{


    Favourite findByPropertyAndPropertyUser(Property property, PropertyUser propertyUser);

    List<Favourite> findByPropertyUser_Id(Long id);


}