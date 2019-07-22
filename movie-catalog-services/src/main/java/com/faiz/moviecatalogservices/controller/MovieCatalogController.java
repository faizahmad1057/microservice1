package com.faiz.moviecatalogservices.controller;

import com.faiz.moviecatalogservices.model.CatalogItem;
import com.faiz.moviecatalogservices.model.Movie;
import com.faiz.moviecatalogservices.model.Rating;
import com.faiz.moviecatalogservices.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/catalog")
public class MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="/{userId}")
    public List<CatalogItem> getMovieDetails(@PathVariable String userId)
    {

       // RestTemplate restTemplate = new RestTemplate();

        UserRating ratings = restTemplate.getForObject("http://localhost:8087/rating/users/" +userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            //For each movie Id call movie info service and get Details
            Movie movie = restTemplate.getForObject("http://localhost:8086/movies/" +rating.getMovieId(), Movie.class);
            //Put them altogether
            return new CatalogItem(movie.getName(),"Nice Movie",rating.getRating());
        })
        .collect(Collectors.toList());
       // return Collections.singletonList(new CatalogItem("Transformers","Nice Movie",4)); // HardCoded Value returned

    }
}
