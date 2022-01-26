package com.pedroblome.user.controller.dto;

import java.math.BigDecimal;

public class StockUpdateDto {
    private Long id;
    private BigDecimal askmin;
    private BigDecimal askmax;
    private BigDecimal bidmin;
    private BigDecimal bidmax;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public StockUpdateDto(){

    }
    public StockUpdateDto(Long id, BigDecimal askmin, BigDecimal askmax, BigDecimal bidmin, BigDecimal bidmax) {
        this.id = id;
        this.askmin = askmin;
        this.askmax = askmax;
        this.bidmin = bidmin;
        this.bidmax = bidmax;
    }
    

    

    
}
