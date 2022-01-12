package com.solinftec.training.solinfbroker.model;

import java.io.Serializable;

public class UserStockBalancesPKId implements Serializable{ 
    private int id_user;
    private int id_stock;

    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public int getId_stock() {
        return id_stock;
    }
    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_stock;
        result = prime * result + id_user;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserStockBalancesPKId other = (UserStockBalancesPKId) obj;
        if (id_stock != other.id_stock)
            return false;
        if (id_user != other.id_user)
            return false;
        return true;
    }

    
}
