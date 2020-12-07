package net.codejava.orderfoodspring.Auth.repository;

import java.util.Optional;
import java.beans.Transient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import net.codejava.orderfoodspring.Auth.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String tendn);

	Boolean existsByUsername(String tendn);
	Boolean existsByEmail(String email);
	Optional<User> findById(Integer id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users set username = ?2, password = ?3, email= ?4 where id= ?1", nativeQuery = true)
	void updateUser(Integer id, String username,String password,String email);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_roles set role_id = ?2 where user_id = ?1", nativeQuery = true)
	void updateRoleUser(Integer user_id, Integer role_id);
}
