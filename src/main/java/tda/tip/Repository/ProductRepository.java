package tda.tip.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tda.tip.entity.Product;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product WHERE id = ?1 ", nativeQuery = true)
    public Product findByProduct(Integer id);
}