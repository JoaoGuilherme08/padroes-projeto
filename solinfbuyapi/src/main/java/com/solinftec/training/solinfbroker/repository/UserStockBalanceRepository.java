package com.solinftec.training.solinfbroker.repository;

import com.solinftec.training.solinfbroker.model.UserStockBalances;
import com.solinftec.training.solinfbroker.model.UserStockBalancesPKId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserStockBalanceRepository extends JpaRepository<UserStockBalances, UserStockBalancesPKId> {

    @Query(value = "SELECT * FROM user_stock_balances WHERE id_user = ?1 and id_stock = ?2", nativeQuery = true)
    UserStockBalances finByUserAndStock(long idUser, long idStock);

    @Query(value = "SELECT * FROM user_stock_balances WHERE id_user = ?1", nativeQuery = true)
    UserStockBalances finByUser(long idUser);

    @Modifying
    @Transactional
    @Query(value = "update user_stock_balances set volume = ?3 where id_user = ?1 and id_stock = ?2", nativeQuery = true)
    void updateBalances(long idUser, long idStock, long volume);
}
