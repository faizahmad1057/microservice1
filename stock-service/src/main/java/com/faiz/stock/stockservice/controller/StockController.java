package com.faiz.stock.stockservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.QuotesProperty;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockController {

    @Autowired
    private RestTemplate restTemplate;

    /* @Autowired
    private YahooFinance yahooFinance;*/

    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") String username){

       // List<String> quotes = restTemplate.getForObject("http://localhost:8300/rest/db"+ username, List.class);

       ResponseEntity<List<String>> quotesResponse = restTemplate.exchange("http://DB-SERVICE/rest/db" + username, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

        List<String> quotes = quotesResponse.getBody();
        return quotes.stream().map(quote -> {
            Stock stock = getStockPrice(quote);
            return new Quote(quote, stock.getQuote().getPrice());
        })
        .collect(Collectors.toList());

    }

    private Stock getStockPrice(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
    }

    private class Quote {

        private String quote;

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        private BigDecimal price;
        public Quote(String quote, BigDecimal price) {

            this.quote=quote;
            this.price=price;
        }
    }
}
