package com.solinftec.training.solinfbroker.services.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class Compra implements Icompra {

    protected String price;

    public Double getMinPriceCompra(Double preco, Long idStock, UserOrderRepository userOrderRepository) {

        return userOrderRepository.selectMinPriceCompra(idStock);
    }

    public Double getMaxPriceCompra(Double preco, Long idStock, UserOrderRepository userOrderRepository) {

        return userOrderRepository.selectMaxPriceCompra(idStock);
    }
}
