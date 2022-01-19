package com.pedroblome.user.Aux;

import com.pedroblome.user.repository.User_orderRepository;

import java.math.BigDecimal;

public class ask implements ask_i {

    @Override
    public BigDecimal AskMax(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository) {
        if (user_orderRepository.selectAskMax(id_stock).compareTo(price) == 1) {
            return price;
        } else {
            return user_orderRepository.selectAskMax(id_stock);

        }
    }

    @Override
    public BigDecimal AskMin(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository) {
        if (user_orderRepository.selectAskMin(id_stock).compareTo(price) == 1) {
            return price;
        } else {
            return user_orderRepository.selectAskMin(id_stock);

        }
    }
}
