package com.pedroblome.user.controller.dto;

import java.math.BigDecimal;

public class BotDto {
    private String name;
    private String email;
    private String password;
    private BigDecimal dollar_balance;
    private Boolean bot;

    public BotDto(){
        
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public BigDecimal getDollar_balance() {
        return dollar_balance;
    }
    public void setDollar_balance(BigDecimal dollar_balance) {
        this.dollar_balance = dollar_balance;
    }
    public Boolean getBot() {
        return bot;
    }
    public void setBot(Boolean bot) {
        this.bot = bot;
    }
    public BotDto(String name, String email, String password, BigDecimal dollar_balance, Boolean bot) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dollar_balance = dollar_balance;
        this.bot = bot;
    }
    
 
    
    
}
