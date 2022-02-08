package com.solinftec.training.solinfbroker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_orders")
public class UserOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "id_stock")
    private long idStock;

    @Column(name = "stock_symbol")
    private String stockSymbol;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "volume")
    private long volume;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    private int type;

    @Column(name = "status")
    private int status;

    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
    private Date createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Date updatedOn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
