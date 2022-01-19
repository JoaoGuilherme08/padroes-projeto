package com.solinftec.training.solinfbroker.controller;

import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.services.UserOrderService.IUserOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("/userorder")
public class UserOrderController {

    
    public UserOrderController(IUserOrderService userOrderService) {
        super();
        this.userOrderService = userOrderService;
    }

    IUserOrderService userOrderService;

    // @GetMapping()
    // public List<UserOrders> listar() {
    //     return userOrderService.Listar();
    // }

    @PostMapping()
    public ResponseEntity<?> adicionar(@RequestHeader("Authorization") String userToken, @RequestBody UserOrders userOrders) {
        System.out.println("Passei Metodo Adicionar");
        return userOrderService.save(userOrders, userToken);
    }

}
