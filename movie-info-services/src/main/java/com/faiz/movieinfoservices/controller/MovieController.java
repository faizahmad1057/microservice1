package com.faiz.movieinfoservices.controller;

import com.faiz.movieinfoservices.model.Movie;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@RequestMapping(value="/movies")
public class MovieController {

    @RequestMapping(value="/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId){

        return new Movie(movieId,"Transformers");


    }


}
