package com.stockapi.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maket_cap")
    private Long marketCap;

    @Column(name = "stock_symbol")
    private String stockSymbol;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "ask_min")
    private Double askMin;

    @Column(name = "ask_max")
    private Double askMax;

    @Column(name = "bid_min")
    private Double bidMin;

    @Column(name = "bid_max")
    private Double bidMax;

    @CreationTimestamp
    @Column(name = "created_on")
    private Date createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Date updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getAskMin() {
        return askMin;
    }

    public void setAskMin(Double askMin) {
        this.askMin = askMin;
    }

    public Double getAskMax() {
        return askMax;
    }

    public void setAskMax(Double askMax) {
        this.askMax = askMax;
    }

    public Double getBidMin() {
        return bidMin;
    }

    public void setBidMin(Double bidMin) {
        this.bidMin = bidMin;
    }

    public Double getBidMax() {
        return bidMax;
    }

    public void setBidMax(Double bidMax) {
        this.bidMax = bidMax;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}
