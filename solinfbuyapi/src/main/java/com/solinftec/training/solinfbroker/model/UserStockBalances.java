package com.solinftec.training.solinfbroker.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(UserStockBalancesPKId.class)
@Table(name ="user_stock_balances")
public class UserStockBalances {
    @Id
    private int id_user;
    @Id
    private int id_stock;
    private String stock_symbol;
    private String stock_name;
    private int volume;
    private Date created_on;
    private Date updated_on;

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
    public String getStock_symbol() {
        return stock_symbol;
    }
    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
    }
    public String getStock_name() {
        return stock_name;
    }
    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public Date getCreated_on() {
        return created_on;
    }
    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
    public Date getUpdated_on() {
        return updated_on;
    }
    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }

    
}
