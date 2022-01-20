package com.solinftec.training.solinfbroker.controller;

import com.solinftec.training.solinfbroker.services.UserOrderBalance.iUserStockBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockbalances")
public class UserStockBalancesController {

    iUserStockBalanceService stockBalanceService;

    public UserStockBalancesController(iUserStockBalanceService stockBalanceService) {
        super();
        this.stockBalanceService = stockBalanceService;
    }

    @GetMapping
    public ResponseEntity<?> listar(@RequestParam(required = true, defaultValue = "0") long idUser,
            @RequestParam(required = false, defaultValue = "0") long idStock) {
        if (idUser != 0) {
            if (idStock != 0) {
                return ResponseEntity.ok(stockBalanceService.finByUserAndStock(idUser, idStock));
            } else {
                return ResponseEntity.ok(stockBalanceService.finByUser(idUser));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario nulo ou igual a zero");
        }
    }
}
