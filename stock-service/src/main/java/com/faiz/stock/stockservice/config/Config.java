package com.faiz.stock.stockservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import yahoofinance.YahooFinance;

@Configuration
public class Config {

    @LoadBalanced
    @Bean
    public RestTemplate template(){

      return new RestTemplate();
    }

    @Bean
    public YahooFinance yahooFinance(){
        return new YahooFinance();
    }


}
