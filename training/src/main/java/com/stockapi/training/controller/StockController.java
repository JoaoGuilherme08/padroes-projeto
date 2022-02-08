package com.stockapi.training.controller;

import java.util.List;
import com.stockapi.training.model.Stock;
import com.stockapi.training.services.istockservice;
import org.springframework.http.ResponseEntity;
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

    istockservice istockservice;

    public StockController(com.stockapi.training.services.istockservice istockservice) {
        super();
        this.istockservice = istockservice;
    }

    @GetMapping
    public List<Stock> Listar() {
        return istockservice.listar();
    }

    @PostMapping
    public Stock adicionar(@RequestBody Stock stock) {
        return istockservice.salvarPost(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceStock(@RequestBody Stock newStock, @PathVariable Long id) {
        return istockservice.salvarAcao(newStock, id);
    }
}
