package com.solinftec.training.solinfbroker.services.UserOrderBalance;

import java.util.List;

import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.model.UserStockBalances;

import org.springframework.http.ResponseEntity;

public interface iUserStockBalanceService {
    UserStockBalances finByUserAndStock(long idUser, long idStock);

    UserStockBalances finByUserAndStockOrder(UserOrders orders);

    UserStockBalances finByUser(long idUser);

    List<UserStockBalances[]> findByEmail(String email);

    ResponseEntity<?> updateStockUser(long idUser, long idStock, long volume);

    UserStockBalances save(UserStockBalances balances);

}
