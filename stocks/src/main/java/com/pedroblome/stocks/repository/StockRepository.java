package com.pedroblome.stocks.repository;

import java.util.List;
import java.util.Optional;

import com.pedroblome.stocks.controller.dto.StockRetornodto;
import com.pedroblome.stocks.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository <Stock, Long>{
    
    


}
