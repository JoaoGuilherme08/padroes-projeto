package com.solinftec.training.solinfbroker.controller;

import java.util.List;

import com.solinftec.training.solinfbroker.model.UserStockBalances;
import com.solinftec.training.solinfbroker.repository.UserStockBalanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockbalances")
public class UserStockBalancesController {
    
    @Autowired
    private UserStockBalanceRepository userStockBalanceRepository;

    @GetMapping
    public List<UserStockBalances> listar() {
        return userStockBalanceRepository.findAll();
    }

    @PostMapping
    public UserStockBalances adicionar(@RequestBody UserStockBalances userStock_Balances){
        return userStockBalanceRepository.save(userStock_Balances);
    }
}
