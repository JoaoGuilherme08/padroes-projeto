package com.solinftec.training.solinfbroker.services.UserOrderService;

import java.util.List;

import com.solinftec.training.solinfbroker.model.UserOrders;

import org.springframework.http.ResponseEntity;

public interface IUserOrderService {
    ResponseEntity<?> save(UserOrders orders, String tokeString);

    List<UserOrders> listaOrdens(int id, long type, long idStock);

    List<UserOrders> findbyUserIdAndType(int idUser, int type);

    List<UserOrders> findByType(int type);

    List<UserOrders> findByUserId(int idUser);
}
