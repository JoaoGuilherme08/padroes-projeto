package com.solinftec.training.solinfbroker.bean;

public interface icompra {
    Double getMinPriceCompra(Double Preco, Long IdStock);
    Double getMaxPriceCompra(Double Preco, Long IdStock);
}
