package com.faiz.ratingsdataservices.controller;


import com.faiz.ratingsdataservices.model.Rating;
import com.faiz.ratingsdataservices.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/rating")
public class RatingsController {

    UserRating userRating = new UserRating();

    @RequestMapping(value="/{movieId}")
    public Rating getRating(@PathVariable String movieId){

        return new Rating(movieId,4);

    }


    @RequestMapping(value="users/{userId}")
    public UserRating getUserRating(@PathVariable String userId){

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        userRating.setUserRating(ratings);
        return userRating;
    }
}
