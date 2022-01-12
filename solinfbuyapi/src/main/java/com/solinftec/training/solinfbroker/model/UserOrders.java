package com.solinftec.training.solinfbroker.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_orders")
public class UserOrders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_user;
    private Long id_stock;
    private String stock_symbol;
    private String stock_name;
    private Integer volume;
    private Double price;
    private Integer type;
    private Integer status;
    private Date created_on;
    private Date updated_on;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId_user() {
        return id_user;
    }
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
    public Long getId_stock() {
        return id_stock;
    }
    public void setId_stock(Long id_stock) {
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
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
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