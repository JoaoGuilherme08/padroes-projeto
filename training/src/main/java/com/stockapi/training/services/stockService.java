package com.stockapi.training.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.stockapi.training.model.Stock;
import com.stockapi.training.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class stockService implements istockservice {

    public stockService(StockRepository stockRepository) {
        super();
        this.stockRepository = stockRepository;
    }

    @Autowired
    private StockRepository stockRepository;

    @Override
    public ResponseEntity<?> salvarAcao(Stock newStock, long id) {

        stockRepository.findById(id)
                .map(stock -> {
                    stock.setAskMin(newStock.getAskMin());
                    stock.setAskMax(newStock.getAskMax());
                    stock.setBidMax(newStock.getBidMax());
                    stock.setBidMin(newStock.getBidMin());
                    stock.setUpdatedOn(Date.from(Instant.now()));
                    return ResponseEntity.ok().body(stockRepository.save(stock));
                }).orElseGet(() -> {
                    newStock.setId(id);
                    return ResponseEntity.ok().body(stockRepository.save(newStock));
                });
        return null;
    }

    @Override
    public List<Stock> listar() {
        return stockRepository.findAllStocks();
    }

    @Override
    public Stock salvarPost(Stock stock) {
        return stockRepository.save(stock);
    }

}
