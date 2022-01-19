package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class compra implements icompra {
    
    protected String price;

    public Double getMinPriceCompra(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        return userOrderRepository.selectMinPriceCompra(IdStock);       
    }


    public Double getMaxPriceCompra(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        return userOrderRepository.selectMaxPriceCompra(IdStock);    
    }    
}
