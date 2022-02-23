package com.solinftec.training.solinfbroker.services.bean;

import com.solinftec.training.solinfbroker.repository.UserOrderRepository;

public interface Icompra {
    Double getMinPriceCompra(Double preco, Long idStock, UserOrderRepository userOrderRepository);

    Double getMaxPriceCompra(Double preco, Long idStock, UserOrderRepository userOrderRepository);
}
