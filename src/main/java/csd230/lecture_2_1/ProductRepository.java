package csd230.lecture_2_1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByName(String name);
    List<Product> findAllByName(String name);
}
