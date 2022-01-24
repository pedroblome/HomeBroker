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

    // public boolean stock (@RequestBody Stock stock) {
    //     if (stockRepository.existsById(stock.getId())) {
    //         if(logica para saber se o nome == symbol == if){
    //             System.out.println("================================");
    //             System.out.println("id stock informado bate na descriçaõ");
    //             System.out.println("================================");

    //                             return true;

    //         }
    //     } else {
    //             System.out.println("================================");
    //             System.out.println("id stock informado NÃO bate na descriçaõ");
    //             System.out.println("================================");
    //         return false;
    //     }
    // }

}
