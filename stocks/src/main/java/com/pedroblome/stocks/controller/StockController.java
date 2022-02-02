package com.pedroblome.stocks.controller;

import com.pedroblome.stocks.controller.dto.StockRetornodto;
import com.pedroblome.stocks.model.Stock;
import com.pedroblome.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
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
        if (stockRepository.existsById(id)) {
            return stockRepository.findById(id);

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Id stock is invalid or doenst exists!"));

        }

    }

    // verify the user_order BUY SELL
    @PostMapping(value = "/dto/{id}")
    public boolean dtoStock(@RequestBody StockRetornodto stockRetornodto) {
        Stock stock = stockRepository.findById(stockRetornodto.getId()).get();
        if (stockRetornodto.getName().equals(stock.getStock_name())
                && stockRetornodto.getSymbol().equals(stock.getStock_symbol())) {
            return true;

        }
        return false;

    }

    @PutMapping("/{id}")
    public Stock replaceStock(@RequestBody Stock newStock, @PathVariable Long id) {

        return stockRepository.findById(id)
                .map(stock -> {
                    stock.setAsk_max(newStock.getAsk_max().compareTo(null) ==0 ? newStock.getAsk_max() : stock.getAsk_max());
                    stock.setAsk_min(newStock.getAsk_min().compareTo(null) ==0 ? newStock.getAsk_min() : stock.getAsk_min());
                    stock.setBid_max(newStock.getBid_max().compareTo(null) ==0 ? newStock.getBid_max() : stock.getBid_max());
                    stock.setBid_min(newStock.getBid_min().compareTo(null) ==0 ? newStock.getBid_min() : stock.getBid_min());
                    return stockRepository.save(stock);

                }).orElseGet(() -> {
                    newStock.setId(id);
                    return stockRepository.save(newStock);
                });

    }

}
