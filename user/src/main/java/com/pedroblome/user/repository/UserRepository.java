package com.pedroblome.user.repository;

import java.util.List;
import java.util.Optional;

import com.pedroblome.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT *FROM User stock where email = ?1 ", nativeQuery = true)
    List<User> getByEmail(String email);
}
