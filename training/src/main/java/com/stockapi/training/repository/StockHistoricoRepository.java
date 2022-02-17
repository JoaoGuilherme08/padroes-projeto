package com.stockapi.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stockapi.training.model.StockHistorico;

@Repository
public interface StockHistoricoRepository extends JpaRepository<StockHistorico, Long> {
}
