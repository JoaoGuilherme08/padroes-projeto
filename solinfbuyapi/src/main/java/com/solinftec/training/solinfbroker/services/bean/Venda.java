package com.solinftec.training.solinfbroker.services.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class Venda implements Ivenda {

    protected String price;

    public Double getMinPriceVenda(Double preco, Long idStock, UserOrderRepository userOrderRepository) {

        return userOrderRepository.selectMinPriceVenda(idStock);

    }

    public Double getMaxPriceVenda(Double preco, Long idStock, UserOrderRepository userOrderRepository) {

        return userOrderRepository.selectMaxPriceVenda(idStock);
    }
}
