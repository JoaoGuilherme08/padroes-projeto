package com.solinftec.training.solinfbroker.repository;

import com.solinftec.training.solinfbroker.model.UserStockBalances;
import com.solinftec.training.solinfbroker.model.UserStockBalancesPKId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStockBalanceRepository extends JpaRepository<UserStockBalances, UserStockBalancesPKId>{
    
}
