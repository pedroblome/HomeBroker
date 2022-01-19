package com.pedroblome.stocks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Stock not found or invalid")
public class StockNotFound extends Exception{

    
}
