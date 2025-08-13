package dev.webshop.nailstore.repository;

import dev.webshop.nailstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?1")
	Optional<User> findByEmailOrUsername(String emailOrUsername);

	@Query("SELECT u.username FROM User u")
	Optional<List<String>> getAllUsernames();
}
