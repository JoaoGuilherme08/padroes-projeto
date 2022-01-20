package com.solinftec.training.solinfbroker.services.UserOrderBalance;

import com.solinftec.training.solinfbroker.model.UserStockBalances;

import org.springframework.http.ResponseEntity;

public interface iUserStockBalanceService {
    UserStockBalances finByUserAndStock(long idUser, long idStock);

    UserStockBalances finByUserAndStock(long idUser, long idStock, UserOrders orders);

    UserStockBalances finByUser(long idUser);

    ResponseEntity<?> updateStockUser(long idUser, long idStock, long volume);
}
