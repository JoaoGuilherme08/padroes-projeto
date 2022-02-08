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

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "username")
    private String username;

    @Column(name = "dollar_balance")
    private Double dollarBalance;

    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getdollarBalance() {
        return dollarBalance;
    }

    public void setdollarBalance(Double dollarBalance) {
        this.dollarBalance = dollarBalance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
