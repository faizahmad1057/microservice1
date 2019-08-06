package com.faiz.stock.dbservice.controller;


import com.faiz.stock.dbservice.model.Quote;
import com.faiz.stock.dbservice.model.Quotes;
import com.faiz.stock.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceController {

    @Autowired
    private QuotesRepository quotesRepository;


    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") String username){


        return getQuotesByUserName(username);

    }


    @PostMapping("/add")
    public List<String> addUser(@RequestBody Quotes quotes){

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote)
                );

        return getQuotesByUserName(quotes.getUserName());

    }

    @PostMapping("/delete/{username}")
    public List<String> deleteUser(@PathVariable("username") String username){

        List<Quote> quotes = quotesRepository.getByUserName(username);
        quotesRepository.delete(quotes);

        return getQuotesByUserName(username);

    }

    public List<String> getQuotesByUserName(String username){

        return quotesRepository.getByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

}
