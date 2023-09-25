package ru.zubkov.jwtauthenticated.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubkov.jwtauthenticated.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
