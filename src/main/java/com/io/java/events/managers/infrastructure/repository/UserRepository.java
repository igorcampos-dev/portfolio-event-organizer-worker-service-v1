package com.io.java.events.managers.infrastructure.repository;

import com.io.java.events.managers.infrastructure.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByEmail(String email);

    default UsersEntity findByEmailOrElseThrow(String email){
        return this.findByEmail(email).orElseThrow( () -> new NullPointerException("User not found with email: " +  email));
    }
}
