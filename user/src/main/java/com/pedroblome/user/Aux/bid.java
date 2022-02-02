// package com.pedroblome.user.Aux;

// import com.pedroblome.user.repository.User_orderRepository;

// import java.math.BigDecimal;

// public  class bid implements bid_i {
//     @Override
//     public BigDecimal BidMax(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository) {
//         if (user_orderRepository.selectAskMax(id_stock).compareTo(price) == 1) {
//             return price;
//         } else {
//             return user_orderRepository.selectAskMax(id_stock);

//         }

//     }

//     @Override
//     public BigDecimal BidMin(BigDecimal price, Long id_stock, User_orderRepository user_orderRepository) {
//         if (user_orderRepository.selectAskMin(id_stock).compareTo(price) == 1) {
//             return price;
//         } else {
//             return user_orderRepository.selectAskMin(id_stock);

//         }
//     }
// }
