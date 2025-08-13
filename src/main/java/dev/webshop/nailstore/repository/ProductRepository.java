package dev.webshop.nailstore.repository;

import dev.webshop.nailstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> getProductsByNameContainingIgnoreCase(String search);

	Optional<Product> findBySlug(String slug);

	Optional<List<Product>> getProductsByCategory_Id(Long categoryId);

	List<Product> findDistinctByTags_NameIn(List<String> tagNames);
}
