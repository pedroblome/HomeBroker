package com.pedroblome.stocks.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.OrderBy;

import com.pedroblome.stocks.controller.dto.StockDto;
import com.pedroblome.stocks.controller.dto.StockRetornodto;
import com.pedroblome.stocks.model.Stock;
import com.pedroblome.stocks.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/stocks")
@CrossOrigin
public class StockController {
    @Autowired
    private StockRepository stockRepository;

  
    @GetMapping
    public List<Stock> list() {
        return stockRepository.findAll(Sort.by("id"));
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

    @PutMapping("/askbid/{id}")
    public ResponseEntity<?> updateStock(@RequestBody StockDto stockDto) {
        System.out.println("informação esta chegando:: " + stockDto.toString());
        Stock stock = stockRepository.findById(stockDto.getId()).get();
        stock.setAsk_max(stockDto.getAsk_max());
        stock.setAsk_min(stockDto.getAsk_min());
        stock.setBid_max(stockDto.getBid_max());
        stock.setBid_min(stockDto.getBid_min());
        stock.setUpdated_on(stockDto.getUpdated_on());

        Stock stockUpdate = stockRepository.save(stock);

        return new ResponseEntity<Stock>(stockUpdate, HttpStatus.CREATED);

    }

}
