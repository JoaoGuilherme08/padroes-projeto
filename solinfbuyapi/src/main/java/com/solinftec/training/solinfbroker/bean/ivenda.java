package com.solinftec.training.solinfbroker.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public interface ivenda {
    Double getMinPriceVenda(Double Preco, Long IdStock, UserOrderRepository userOrderRepository);
    Double getMaxPriceVenda(Double Preco, Long IdStock, UserOrderRepository userOrderRepository);
}
