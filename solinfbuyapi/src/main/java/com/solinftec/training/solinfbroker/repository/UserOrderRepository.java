package com.solinftec.training.solinfbroker.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.solinftec.training.solinfbroker.model.UserOrders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserOrderRepository extends JpaRepository<UserOrders, Long> {
    
    @Query(value = "Select coalesce(min(price),0) from user_orders where type = '2' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMinPriceVenda(Long id_stock);

    @Query(value = "Select coalesce(max(price),0) from user_orders where type = '2' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMaxPriceVenda(Long id_stock);

    @Query(value = "Select coalesce(min(price),0) from user_orders where type = '1' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMinPriceCompra(Long id_stock);

    @Query(value = "Select coalesce(max(price),0) from user_orders where type = '1' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMaxPriceCompra(Long id_stock);


    @Query(value = "SELECT * FROM user_orders uo WHERE uo.type = ?1 and id_stock = ?2 and status = 1 and id_user != ?3 order by case when uo.type = 2 then price end asc, case when uo.type = 2 then created_on end asc, case when uo.type = 1 then created_on end asc", nativeQuery = true)
    List<UserOrders> findByTypeAndStockNotId(int type, long stock, long id);

    @Query(value = "SELECT * FROM user_orders WHERE id_user = ?1", nativeQuery = true)
    List<UserOrders> findByUser(int idUser);

    @Query(value = "SELECT * FROM user_orders WHERE id = ?1", nativeQuery = true)
    UserOrders findId(long id);

    @Query(value = "SELECT * FROM user_orders WHERE id_user = ?1 and type = ?2", nativeQuery = true)
    List<UserOrders> findByUserIdAndType(int idUser, int type);

}
