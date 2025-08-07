package dev.webshop.nailstore.repository;

import dev.webshop.nailstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findBySlug(String slug);
}