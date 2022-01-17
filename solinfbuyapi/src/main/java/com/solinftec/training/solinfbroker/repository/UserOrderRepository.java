package com.solinftec.training.solinfbroker.repository;

import javax.transaction.Transactional;

import com.solinftec.training.solinfbroker.model.UserOrders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserOrderRepository extends JpaRepository<UserOrders, Long> {
    
    @Query(value = "Select coalesce(min(price),0) from user_orders where type = '1' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMinPriceVenda(Long id_stock);

    @Query(value = "Select coalesce(max(price),0) from user_orders where type = '1' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMaxPriceVenda(Long id_stock);

    @Query(value = "Select coalesce(min(price),0) from user_orders where type = '0' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMinPriceCompra(Long id_stock);

    @Query(value = "Select coalesce(max(price),0) from user_orders where type = '0' and status = '1' and id_stock = ?1",nativeQuery = true)
    Double selectMaxPriceCompra(Long id_stock);


}
