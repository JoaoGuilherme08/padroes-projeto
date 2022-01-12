package com.solinftec.training.solinfbroker.repository;

import com.solinftec.training.solinfbroker.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
