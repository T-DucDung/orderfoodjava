package net.codejava.orderfoodspring.Auth.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.orderfoodspring.Auth.models.ERole;
import net.codejava.orderfoodspring.Auth.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(ERole name);
    
}
