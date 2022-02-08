package com.solinftec.training.solinfbroker.model;

import java.io.Serializable;

import javax.persistence.Column;

public class UserStockBalancesPKId implements Serializable {

    @Column(name = "id_user")
    private long idUser;

    @Column(name = "id_stock")
    private long idStock;

    public long getidUser() {
        return idUser;
    }

    public void setidUser(long idUser) {
        this.idUser = idUser;
    }

    public long getidStock() {
        return idStock;
    }

    public void setidStock(long idStock) {
        this.idStock = idStock;
    }

}
