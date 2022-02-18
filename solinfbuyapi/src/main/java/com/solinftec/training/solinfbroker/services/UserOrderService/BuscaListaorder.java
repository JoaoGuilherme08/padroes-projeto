package com.solinftec.training.solinfbroker.services.UserOrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solinftec.training.solinfbroker.model.UserOrders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BuscaListaorder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserOrders> shareInverseOrder(UserOrders orders) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idStock", orders.getidStock());
        parameters.put("typeOrder", orders.getType() == 1 ? 2 : 1);
        parameters.put("idUser", orders.getidUser());

        String compare = orders.getType() == 1 ? "uo.price <= " + orders.getPrice() + ""
                : "uo.price >= " + orders.getPrice() + "";

        return namedParameterJdbcTemplate.query("SELECT * FROM user_orders uo "
                + "WHERE uo.type = :typeOrder "
                + "and id_stock = :idStock "
                + "and status = 1 "
                + "and id_user != :idUser "
                + "and " + compare + ""
                + "order by case when uo.type = 2 "
                + "then price end asc, "
                + "case when uo.type = 2 "
                + "then created_on end asc, "
                + "case when uo.type = 1 "
                + "then created_on end asc", parameters, new BeanPropertyRowMapper<>(UserOrders.class));

    }
}
