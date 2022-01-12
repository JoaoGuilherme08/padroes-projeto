package com.stockapi.training.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long market_cap;
    private String stock_symbol;
    private String stock_name;
    private Double ask_min;
    private Double ask_max;
    private Double bid_min;
    private Double bid_max;
    private Date created_on;
    private Date updated_on;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMarket_cap() {
        return market_cap;
    }
    public void setMarket_cap(Long market_cap) {
        this.market_cap = market_cap;
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
    public Double getAsk_min() {
        return ask_min;
    }
    public void setAsk_min(Double ask_min) {
        this.ask_min = ask_min;
    }
    public Double getAsk_max() {
        return ask_max;
    }
    public void setAsk_max(Double ask_max) {
        this.ask_max = ask_max;
    }
    public Double getBid_min() {
        return bid_min;
    }
    public void setBid_min(Double bid_min) {
        this.bid_min = bid_min;
    }
    public Double getBid_max() {
        return bid_max;
    }
    public void setBid_max(Double bid_max) {
        this.bid_max = bid_max;
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
