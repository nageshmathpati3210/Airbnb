package com.basic.airbnb.controller;


import com.basic.airbnb.entity.Property;
import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.entity.Review;
import com.basic.airbnb.payload.ReviewDto;
import com.basic.airbnb.repository.PropertyRepository;
import com.basic.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController
{


    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addUser/{propertyId}")
    public ResponseEntity<?> addReview(@PathVariable long propertyId, @RequestBody Review review, @AuthenticationPrincipal PropertyUser propertyUser) {

            Optional<Property> byId = propertyRepository.findById(propertyId);
            Property property = byId.get();


        Review reviewByUser = reviewRepository.findReviewByUser(property, propertyUser);
        if(reviewByUser!=null)
        {
            return new ResponseEntity<>("You have already a review for this property",HttpStatus.BAD_REQUEST);
        }
        review.setProperty(property);
            review.setPropertyUser(propertyUser);
            Review save = reviewRepository.save(review);
            return new ResponseEntity<>("Review Added Sucessfully", HttpStatus.OK);
        }


        @GetMapping("/userReviews")
        public ResponseEntity<?> getAllReviewBasedOnProperty(@AuthenticationPrincipal PropertyUser user)
        {
               return new ResponseEntity<>(reviewRepository.findByPropertyUser(user),HttpStatus.OK);

        }


}
