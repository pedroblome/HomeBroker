package com.pedroblome.stocks.model;

import javax.persistence.*;

import com.pedroblome.stocks.repository.StockRepository;

import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name= "id")
    private Long id;
    private String stock_symbol;
    private String stock_name;
    private BigDecimal ask_min;
    private BigDecimal ask_max;
    private BigDecimal bid_min;
    private BigDecimal bid_max;
    private Timestamp created_on;
    private Timestamp updated_on;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getAsk_min() {
        return ask_min;
    }

    public void setAsk_min(BigDecimal ask_min) {
        this.ask_min = ask_min;
    }

    public BigDecimal getAsk_max() {
        return ask_max;
    }

    public void setAsk_max(BigDecimal ask_max) {
        this.ask_max = ask_max;
    }

    public BigDecimal getBid_min() {
        return bid_min;
    }

    public void setBid_min(BigDecimal bid_min) {
        this.bid_min = bid_min;
    }

    public BigDecimal getBid_max() {
        return bid_max;
    }

    public void setBid_max(BigDecimal bid_max) {
        this.bid_max = bid_max;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }
    public Stock(){
        this.created_on = Timestamp.valueOf(LocalDateTime.now());
        this.updated_on = Timestamp.valueOf(LocalDateTime.now());
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result();
    }

    private int result() {
        return 0;
    }
    

}
