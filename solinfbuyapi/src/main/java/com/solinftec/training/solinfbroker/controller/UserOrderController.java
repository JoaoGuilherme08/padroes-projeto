package com.solinftec.training.solinfbroker.controller;

import java.util.List;

import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userorder")
public class UserOrderController {
    
    @Autowired
    private UserOrderRepository userOrderRepository;

    @GetMapping
    public List<UserOrders> listar() {
        return userOrderRepository.findAll();
    }

    @PostMapping
    public UserOrders adicionar(@RequestBody UserOrders userOrders){
        return userOrderRepository.save(userOrders);
    }
}
