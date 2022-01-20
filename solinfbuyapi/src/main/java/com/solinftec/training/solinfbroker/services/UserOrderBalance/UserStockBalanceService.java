package com.solinftec.training.solinfbroker.services.UserOrderBalance;

import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.model.UserStockBalances;
import com.solinftec.training.solinfbroker.repository.UserStockBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserStockBalanceService implements iUserStockBalanceService {

    @Autowired
    private UserStockBalanceRepository userStockBalanceRepository;

    @Override
    public UserStockBalances finByUserAndStock(long idUser, long idStock) {
        if (userStockBalanceRepository.finByUserAndStock(idUser, idStock) == null) {
            return new UserStockBalances();
        } else {
            return userStockBalanceRepository.finByUserAndStock(idUser, idStock);
        }
    }

    @Override
    public UserStockBalances finByUserAndStock(long idUser, long idStock, UserOrders orders) {
        if (userStockBalanceRepository.finByUserAndStock(idUser, idStock) != null) {
            return userStockBalanceRepository.finByUserAndStock(idUser, idStock);
        } else {
            UserStockBalances newBalance = new UserStockBalances();
            newBalance.setId_stock(idStock);
            newBalance.setId_user(idUser);
            newBalance.setStock_name(orders.getStock_name());
            newBalance.setStock_symbol(orders.getStock_symbol());
            newBalance.setVolume(0);
            userStockBalanceRepository.save(newBalance);

            return (newBalance);
        }
    }

    @Override
    public UserStockBalances finByUser(long idUser) {
        return userStockBalanceRepository.finByUser(idUser);
    }

    @Override
    public ResponseEntity<?> updateStockUser(long idUser, long idStock, long volume) {
        return ResponseEntity.ok().body(userStockBalanceRepository.updateBalances(idUser, idStock, volume));
    }
}
