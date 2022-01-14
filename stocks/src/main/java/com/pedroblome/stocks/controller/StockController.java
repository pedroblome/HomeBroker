package com.pedroblome.stocks.controller;

import com.pedroblome.stocks.model.Stock;
import com.pedroblome.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public List<Stock> list() {
        return stockRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Stock> searchStock(@PathVariable Long id) {
        return stockRepository.findById(id);
    }

    @PostMapping
    public Stock addStock(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        stockRepository.deleteById(id);
    }

}
