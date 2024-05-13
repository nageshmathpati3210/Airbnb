package com.basic.airbnb.repository;

import java.util.List;
import java.util.Optional;

import com.basic.airbnb.entity.Property;
import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.property = :property AND r.propertyUser =:propertyUser")
    Review findReviewByUser(@Param("property") Property  property, @Param("propertyUser") PropertyUser propertyUser);

    List<Review> findByPropertyUser(PropertyUser user);


}
