package com.stockapi.training.controller;

import java.util.List;

import com.stockapi.training.model.Stock;
import com.stockapi.training.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
