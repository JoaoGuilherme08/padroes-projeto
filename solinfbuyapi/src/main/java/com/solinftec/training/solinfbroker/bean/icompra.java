package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public interface icompra {
    Double getMinPriceCompra(Double Preco, Long IdStock, UserOrderRepository userOrderRepository);
    Double getMaxPriceCompra(Double Preco, Long IdStock, UserOrderRepository userOrderRepository);
}
