package com.solinftec.training.solinfbroker.model;

import java.io.Serializable;

public class UserStockBalancesPKId implements Serializable {
    private long id_user;
    private long id_stock;

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public long getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

}
