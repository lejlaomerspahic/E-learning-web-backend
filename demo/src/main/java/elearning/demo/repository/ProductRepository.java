package elearning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.demo.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
