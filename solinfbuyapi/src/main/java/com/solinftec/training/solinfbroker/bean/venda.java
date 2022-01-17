package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public class venda implements ivenda {

    protected String price;


    public Double getMinPriceVenda(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        if(userOrderRepository.selectMinPriceVenda(IdStock) > Preco && userOrderRepository.selectMinPriceVenda(IdStock) != 0){
            return Preco;
        }else{
            return userOrderRepository.selectMinPriceVenda(IdStock);
        }
    }


    public Double getMaxPriceVenda(Double Preco, Long IdStock, UserOrderRepository userOrderRepository){

        if(userOrderRepository.selectMaxPriceVenda(IdStock) > Preco && userOrderRepository.selectMinPriceVenda(IdStock) != 0){
            return Preco;
        }else{
            return userOrderRepository.selectMaxPriceVenda(IdStock);
        }
    }
}
