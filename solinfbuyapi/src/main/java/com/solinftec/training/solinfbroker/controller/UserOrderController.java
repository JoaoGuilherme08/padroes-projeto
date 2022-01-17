package com.solinftec.training.solinfbroker.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.solinftec.training.solinfbroker.bean.compra;
import com.solinftec.training.solinfbroker.bean.venda;
import com.solinftec.training.solinfbroker.model.UserOrders;
import com.solinftec.training.solinfbroker.repository.UserOrderRepository;
import com.zaxxer.hikari.HikariJNDIFactory;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/userorder")
public class UserOrderController {
    
    @Autowired
    private UserOrderRepository userOrderRepository;

    @GetMapping
    public List<UserOrders> listar() {
        return userOrderRepository.findAll();
    }

    @PostMapping()
    public UserOrders adicionar(@RequestHeader("Authorization") String userToken, @RequestBody UserOrders userOrders){

        var venda = new venda();
        
        var compra = new compra();

            UriComponents uri = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost:8084")
            .path("stock/{id}")
            .build();
            
            RestTemplate template = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", userToken);

            Map<String, Long> param = new HashMap<String, Long>();
            param.put("id", userOrders.getId_stock());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", userOrders.getId_stock());
            jsonObject.put("ask_min", venda.getMinPriceVenda(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));
            jsonObject.put("ask_max", venda.getMaxPriceVenda(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));
            jsonObject.put("bid_min", compra.getMinPriceCompra(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));
            jsonObject.put("bid_max", compra.getMaxPriceCompra(userOrders.getPrice(), userOrders.getId_stock(), userOrderRepository));

            HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
            
            HttpEntity<String> response = template.exchange(uri.toString(), HttpMethod.PUT, request, String.class, param);

        return userOrderRepository.save(userOrders);
    }
}
