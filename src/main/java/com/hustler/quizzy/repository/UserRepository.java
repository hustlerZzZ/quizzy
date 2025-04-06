package com.hustler.quizzy.repository;

import java.util.Optional;
import com.hustler.quizzy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
