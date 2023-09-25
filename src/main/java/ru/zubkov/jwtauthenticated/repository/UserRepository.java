package ru.zubkov.jwtauthenticated.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubkov.jwtauthenticated.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);
}
