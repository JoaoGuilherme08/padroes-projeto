package com.stockapi.training.services;

import java.util.List;

import com.stockapi.training.model.Stock;

import org.springframework.http.ResponseEntity;

public interface istockservice {
    ResponseEntity<?> salvarAcao(Stock stock, long Id);

    List<Stock> listar();

    Stock salvarPost(Stock stock);
}
