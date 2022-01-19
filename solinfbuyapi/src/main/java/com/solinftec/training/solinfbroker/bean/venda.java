package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class venda implements ivenda {

    protected String price;


    public Double getMinPriceVenda(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        return userOrderRepository.selectMinPriceVenda(IdStock);

    }


    public Double getMaxPriceVenda(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        return userOrderRepository.selectMaxPriceVenda(IdStock);        
    }
}
