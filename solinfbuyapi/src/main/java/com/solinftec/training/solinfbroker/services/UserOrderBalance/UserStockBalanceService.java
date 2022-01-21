package com.solinftec.training.solinfbroker.services.UserOrderBalance;

import java.util.List;

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
    public UserStockBalances finByUserAndStockOrder(UserOrders orders) {
        if (userStockBalanceRepository.finByUserAndStock(orders.getId_user(), orders.getId_stock()) != null) {
            return userStockBalanceRepository.finByUserAndStock(orders.getId_user(), orders.getId_stock());
        } else {
            UserStockBalances newBalance = new UserStockBalances();
            newBalance.setId_stock(orders.getId_stock());
            newBalance.setId_user(orders.getId_user());
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
        userStockBalanceRepository.updateBalances(idUser, idStock, volume);
        return ResponseEntity.ok().body("Usuario Updatade");
    }

    @Override
    public UserStockBalances save(UserStockBalances balances) {

        return userStockBalanceRepository.save(balances);
    }

    @Override
    public List<UserStockBalances[]> findByEmail(String email) {

        return userStockBalanceRepository.findByEmail(email);
    }

}
