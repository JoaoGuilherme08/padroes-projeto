package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class venda implements ivenda {

    protected String price;


    private UserOrderRepository userOrderRepository;


    public Double getMinPriceVenda(Double Preco, Long IdStock){

        if(userOrderRepository.selectMinPriceVenda(IdStock) > Preco){
            return Preco;
        }else{
            return userOrderRepository.selectMinPriceVenda(IdStock);
        }
    }


    public Double getMaxPriceVenda(Double Preco, Long IdStock){

        if(userOrderRepository.selectMaxPriceVenda(IdStock) > Preco){
            return Preco;
        }else{
            return userOrderRepository.selectMaxPriceVenda(IdStock);
        }
    }
}
