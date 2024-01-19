package io.srk.auth.repository;

import io.srk.auth.model.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmailOrUsername(String email, String username);
}
