package com.faiz.stock.dbservice.repository;

import com.faiz.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {
  List<Quote> getByUserName(String userName);
}
