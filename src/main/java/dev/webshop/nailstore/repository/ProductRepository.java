package dev.webshop.nailstore.repository;

import dev.webshop.nailstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> getProductsByNameContainingIgnoreCase(String search);
}
