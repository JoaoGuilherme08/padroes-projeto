package com.stockapi.training.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.stockapi.training.model.Stock;
import com.stockapi.training.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public List<Stock> Listar() {
        return stockRepository.findAllStocks();
    }

    @PostMapping
    public Stock adicionar(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    @PutMapping("/{id}")
    public Stock replaceStock(@RequestBody Stock newStock, @PathVariable Long id) {

        return stockRepository.findById(id)
                .map(stock -> {
                    stock.setAsk_min(newStock.getAsk_min());
                    stock.setAsk_max(newStock.getAsk_max());
                    stock.setBid_max(newStock.getBid_max());
                    stock.setBid_min(newStock.getBid_min());
                    stock.setUpdated_on(Date.from(Instant.now()));
                    return stockRepository.save(stock);

                }).orElseGet(() -> {
                    newStock.setId(id);
                    return stockRepository.save(newStock);
                });
    }

}
