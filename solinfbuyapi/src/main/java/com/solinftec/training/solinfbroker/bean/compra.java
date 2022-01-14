package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class compra implements icompra {
    
    protected String price;
    private UserOrderRepository userOrderRepository;


    public Double getMinPriceCompra(Double Preco, Long IdStock){

        if(userOrderRepository.selectMinPriceCompra(IdStock) > Preco){
            return Preco;
        }else{
            return userOrderRepository.selectMinPriceCompra(IdStock);
        }
    }


    public Double getMaxPriceCompra(Double Preco, Long IdStock){

        if(userOrderRepository.selectMaxPriceCompra(IdStock) > Preco){
            return Preco;
        }else{
            return userOrderRepository.selectMaxPriceCompra(IdStock);
        }
    }
    
}
