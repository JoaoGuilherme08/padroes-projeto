package com.solinftec.training.solinfbroker.services.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public interface Ivenda {
    Double getMinPriceVenda(Double preco, Long idStock, UserOrderRepository userOrderRepository);

    Double getMaxPriceVenda(Double preco, Long idStock, UserOrderRepository userOrderRepository);
}
