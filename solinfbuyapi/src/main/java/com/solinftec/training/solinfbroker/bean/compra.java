package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class compra implements icompra {
    
    protected String price;

    public Double getMinPriceCompra(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        if(userOrderRepository.selectMinPriceCompra(IdStock) > Preco && userOrderRepository.selectMinPriceVenda(IdStock) != 0){
            return Preco;
        }else{
            return userOrderRepository.selectMinPriceCompra(IdStock);
        }
    }


    public Double getMaxPriceCompra(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        if(userOrderRepository.selectMaxPriceCompra(IdStock) > Preco && userOrderRepository.selectMinPriceVenda(IdStock) != 0){
            return Preco;
        }else{
            return userOrderRepository.selectMaxPriceCompra(IdStock);
        }
    }
    
}
