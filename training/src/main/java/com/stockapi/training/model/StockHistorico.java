package com.stockapi.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "historystock")
public class StockHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
