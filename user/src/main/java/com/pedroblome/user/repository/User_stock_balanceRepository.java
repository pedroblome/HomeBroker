package com.pedroblome.user.repository;

import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.pedroblome.user.model.User_stock_ballance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface User_stock_balanceRepository extends JpaRepository<User_stock_ballance, Long> {
    //consulta no banco user_stock_ballance where dado um id_user;
    @Query("SELECT  new com.pedroblome.user.model.User_stock_ballance ( stock.id_user, stock.id_stock, stock.stock_name, stock.stock_symbol,stock.volume) FROM User_stock_ballance stock where stock.id_user = :id_user ")
    public List<User_stock_ballance> findIduser(@Param("id_user") Long  id_user);
    
    
    
    
}

