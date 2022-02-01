package com.pedroblome.stocks.controller.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StockUpdateDto {
    private long id;
    private BigDecimal askmin;
    private BigDecimal askmax;
    private BigDecimal bidmin;
    private BigDecimal bidmax;
    private Timestamp updated_on;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAskmin() {
        return askmin;
    }

    public void setAskmin(BigDecimal askmin) {
        this.askmin = askmin;
    }

    public BigDecimal getAskmax() {
        return askmax;
    }

    public void setAskmax(BigDecimal askmax) {
        this.askmax = askmax;
    }

    public BigDecimal getBidmin() {
        return bidmin;
    }

    public void setBidmin(BigDecimal bidmin) {
        this.bidmin = bidmin;
    }

    public BigDecimal getBidmax() {
        return bidmax;
    }

    public void setBidmax(BigDecimal bidmax) {
        this.bidmax = bidmax;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

    public StockUpdateDto(long id, BigDecimal askmin, BigDecimal askmax, BigDecimal bidmin, BigDecimal bidmax,
            Timestamp updated_on) {
        this.id = id;
        this.askmin = askmin;
        this.askmax = askmax;
        this.bidmin = bidmin;
        this.bidmax = bidmax;
        this.updated_on = Timestamp.valueOf(LocalDateTime.now());
        ;
    }

}
