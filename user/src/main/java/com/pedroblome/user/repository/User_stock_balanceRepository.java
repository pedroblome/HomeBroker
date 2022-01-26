package com.pedroblome.user.repository;

import java.util.List;

import com.pedroblome.user.model.User_stock_balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface User_stock_balanceRepository extends JpaRepository<User_stock_balance, Long> {
    User_stock_balance getByIdUser = null;

    //stocks por usuario
    @Query("SELECT  new com.pedroblome.user.model.User_stock_balance ( stock.id_user, stock.id_stock, stock.stock_name, stock.stock_symbol,stock.volume) FROM User_stock_balance stock where stock.id_user = :id_user ")
    List<User_stock_balance> findStockByUser(@Param("id_user") Long id_user);

    @Query("SELECT  new com.pedroblome.user.model.User_stock_balance (stock.volume) FROM User_stock_balance stock where (stock.id_user = :id_user) and (stock.id_stock = :id_stock)")
    User_stock_balance volume(@Param("id_user") Long id_user,@Param("id_stock") Long id_stock);
    
    @Query("SELECT  new com.pedroblome.user.model.User_stock_balance (stock.remaing_volume) FROM User_stock_balance stock where (stock.id_user = :id_user) and (stock.id_stock = :id_stock)")
    User_stock_balance remaingvolume(@Param("id_user") Long id_user,@Param("id_stock") Long id_stock);



   





}

