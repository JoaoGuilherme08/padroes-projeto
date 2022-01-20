package com.solinftec.training.solinfbroker.repository;

import com.solinftec.training.solinfbroker.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM users WHERE id= ?1", nativeQuery = true)
    Users findId(long id);

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    Users findUserExistente(String email);
}
