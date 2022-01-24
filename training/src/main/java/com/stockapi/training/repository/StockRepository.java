package com.stockapi.training.repository;

import java.util.List;

import com.stockapi.training.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "SELECT * from stocks order by id", nativeQuery = true)
    List<Stock> findAllStocks();
}
