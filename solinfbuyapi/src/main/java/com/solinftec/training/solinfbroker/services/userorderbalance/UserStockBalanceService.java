package com.solinftec.training.solinfbroker.services.userOrderBalance;

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
        if (userStockBalanceRepository.finByUserAndStock(orders.getidUser(), orders.getidStock()) != null) {
            return userStockBalanceRepository.finByUserAndStock(orders.getidUser(), orders.getidStock());
        } else {
            UserStockBalances newBalance = new UserStockBalances();
            newBalance.setidStock(orders.getidStock());
            newBalance.setidUser(orders.getidUser());
            newBalance.setstockName(orders.getstockName());
            newBalance.setstockSymbol(orders.getstockSymbol());
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
