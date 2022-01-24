package com.pedroblome.user.controller.dto;

import java.math.BigDecimal;

public class Userdto {
    private Long id;
    private String name;
    private BigDecimal ballance;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getBallance() {
        return ballance;
    }
    public void setBallance(BigDecimal ballance) {
        this.ballance = ballance;
    }
    public Userdto(Long id, String name, BigDecimal ballance) {
        this.id = id;
        this.name = name;
        this.ballance = ballance;
    }

    
    
}
