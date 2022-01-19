package com.solinftec.training.solinfbroker.services.UserOrderService;

import java.util.List;

import com.solinftec.training.solinfbroker.model.UserOrders;

import org.springframework.http.ResponseEntity;

public interface IUserOrderService {
    ResponseEntity<?> save(UserOrders orders, String tokeString);
    List listaOrdens(int id, long type, long idStock);
}
