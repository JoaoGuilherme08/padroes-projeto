package com.solinftec.training.solinfbroker.repository;

import com.solinftec.training.solinfbroker.model.UserOrders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrders, Long> {
    
}
