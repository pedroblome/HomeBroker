package com.pedroblome.stocks.controller;

import com.pedroblome.stocks.model.Stock;
import com.pedroblome.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    // @GetMapping(value = "/{id}")
    // public Optional<Stock> searchStock(@PathVariable Long id) {
    //     if (stockRepository.existsById(id)) {
    //         return stockRepository.findById(id);

    //     } else {
    //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
    //                 String.format("Id stock is invalid or doenst exists: ", id));

    //     }

    // }
    @GetMapping(value = "/{id}")
    public Optional<Stock> searchStock(@PathVariable Long id) {

        return stockRepository.findById(id);
    }
    // @GetMapping("{id}")
    // public Stock searchStock(@PathVariable Long id)  {
    // return stockRepository.getById(id);
}
