package com.solinftec.training.solinfbroker.controller;

import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.services.UserOrderService.IUserOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/userorder")
public class UserOrderController {

    public UserOrderController(IUserOrderService userOrderService) {
        super();
        this.userOrderService = userOrderService;
    }

    IUserOrderService userOrderService;

    @GetMapping
    public ResponseEntity<?> listAll(@RequestParam(required = false, defaultValue = "0") int user,
            @RequestParam(required = false, defaultValue = "0") int type,
            @RequestParam(required = false, defaultValue = "0") int stock) {
        if (user != 0 && type == 0) {
            return ResponseEntity.ok().body(userOrderService.findByUserId(user));
        } else if (user != 0 && type != 0) {
            return ResponseEntity.ok().body(userOrderService.findbyUserIdAndType(user, type));
        } else if (type != 0) {
            return ResponseEntity.ok().body(userOrderService.findByType(type));
        } else {
            return ResponseEntity.badRequest().body("Erro ao listar a ordem.");
        }
    }

    @PostMapping()
    public ResponseEntity<?> adicionar(@RequestHeader("Authorization") String userToken,
            @RequestBody UserOrders userOrders) {
        return userOrderService.save(userOrders, userToken);
    }

}
