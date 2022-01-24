package com.pedroblome.stocks.controller.dto;

public class StockRetornodto {
    private long id;
    private String name;
    private String symbol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockRetornodto(long id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "StockRetornodto [id=" + id + ", name=" + name + ", symbol=" + symbol + "]";
    }
    

}
