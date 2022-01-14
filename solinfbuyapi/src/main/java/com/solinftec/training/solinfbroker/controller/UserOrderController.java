package com.solinftec.training.solinfbroker.controller;

import java.util.List;

import com.solinftec.training.solinfbroker.bean.compra;
import com.solinftec.training.solinfbroker.bean.venda;
import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

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

        var venda = new venda();
        
        var compra = new compra();

        venda.getMinPriceVenda(userOrders.getPrice(), userOrders.getId_stock());
        venda.getMaxPriceVenda(userOrders.getPrice(), userOrders.getId_stock());
        compra.getMaxPriceCompra(userOrders.getPrice(), userOrders.getId_stock());
        compra.getMinPriceCompra(userOrders.getPrice(), userOrders.getId_stock());

            // RestTemplate template = new RestTemplate();

            // template.getForObject(url, responseType, uriVariables)

            // template.postForObject(url, request, responseType, uriVariables)
       

        return userOrderRepository.save(userOrders);
    }
}
