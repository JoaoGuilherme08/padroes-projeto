package com.stockapi.training.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.stockapi.training.model.Stock;
import com.stockapi.training.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    
    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public List<Stock> Listar(){
        return stockRepository.findAll();
    }

    @PostMapping
    public Stock adicionar(@RequestBody Stock stock){
        return stockRepository.save(stock);
    }

    @PutMapping("/{id}")
    public Stock replaceStock(@RequestBody Stock newStock, @PathVariable Long id){
       
        return stockRepository.findById(id)
         .map(stock -> {
            stock.setAsk_max(newStock.getAsk_max() != 0 ? newStock.getAsk_max() : stock.getAsk_max());
            stock.setAsk_min(newStock.getAsk_min() != 0 ? newStock.getAsk_min() : stock.getAsk_min());
            stock.setBid_max(newStock.getBid_max() != 0 ? newStock.getBid_max() : stock.getBid_max());
            stock.setBid_min(newStock.getBid_min() != 0 ? newStock.getBid_min() : stock.getBid_min());
            stock.setUpdated_on(Date.from(Instant.now()));
            return stockRepository.save(stock);

        }).orElseGet(() -> {
            newStock.setId(id);
            return stockRepository.save(newStock);
        });

    }
}
