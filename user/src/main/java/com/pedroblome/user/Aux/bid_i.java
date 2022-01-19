package com.pedroblome.user.Aux;

import com.pedroblome.user.repository.User_orderRepository;

import java.math.BigDecimal;

public interface bid_i {
    BigDecimal BidMin(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository);
    BigDecimal BidMax(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository);

}
