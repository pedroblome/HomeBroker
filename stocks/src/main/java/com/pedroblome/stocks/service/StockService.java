package com.pedroblome.stocks.service;

import com.pedroblome.stocks.model.Stock;
import com.pedroblome.stocks.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

}
