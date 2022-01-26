package com.pedroblome.user.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import com.pedroblome.user.model.User_order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface User_orderRepository extends JpaRepository<User_order, Long> {

    @Query(value = "Select coalesce(min(price),0) from user_order where type= '0' and status = '0'and id_stock = ?1", nativeQuery = true)
    BigDecimal selectAskMin(Long id_stock);

    @Query(value = "Select coalesce(max(price),0) from user_order where type= '0' and status = '0' ", nativeQuery = true)
    BigDecimal selectAskMax(Long id_stock);

    @Query(value = "Select coalesce(min(price),0) from user_order where type= '1' and status = '0'and id_stock = ?1", nativeQuery = true)
    BigDecimal selectBidMin(Long id_stock);

    @Query(value = "Select coalesce(max(price),0) from user_order where type= '1' and status = '0' ", nativeQuery = true)
    BigDecimal selectBidMax(Long id_stock);

   

    

      


}
