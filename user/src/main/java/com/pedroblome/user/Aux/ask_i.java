package com.pedroblome.user.Aux;

import com.pedroblome.user.repository.User_orderRepository;

import java.math.BigDecimal;

public interface ask_i {
    BigDecimal AskMin(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository);
    BigDecimal AskMax(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository);

}
