package com.solinftec.training.solinfbroker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@IdClass(UserStockBalancesPKId.class)
@Table(name = "user_stock_balances")
public class UserStockBalances {

    @Id
    @Column(name = "id_user")
    private long idUser;

    @Id
    @Column(name = "id_stock")
    private long idStock;

    @Column(name = "stock_symbol")
    private String stockSymbol;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "volume")
    private long volume;

    @CreationTimestamp
    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private Date updatedOn;

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

    public String getstockSymbol() {
        return stockSymbol;
    }

    public void setstockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getstockName() {
        return stockName;
    }

    public void setstockName(String stockName) {
        this.stockName = stockName;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public Date getcreatedOn() {
        return createdOn;
    }

    public void setcreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getupdatedOn() {
        return updatedOn;
    }

    public void setupdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}
