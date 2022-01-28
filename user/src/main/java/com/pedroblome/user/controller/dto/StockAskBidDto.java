package com.pedroblome.user.controller.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StockAskBidDto {
    private long id;
    private BigDecimal ask_min;
    private BigDecimal ask_max;
    private BigDecimal bid_min;
    private BigDecimal bid_max;
    private Timestamp updated_on;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

    public StockAskBidDto(long id, BigDecimal ask_min, BigDecimal ask_max, BigDecimal bid_min, BigDecimal bid_max,
            Timestamp updated_on) {
        this.id = id;
        this.ask_min = ask_min;
        this.ask_max = ask_max;
        this.bid_min = bid_min;
        this.bid_max = bid_max;
        this.updated_on = Timestamp.valueOf(LocalDateTime.now());
    }
}
