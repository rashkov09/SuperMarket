package application.supermarket.repository;

import application.supermarket.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product getByName(String productName);

    @Query("SELECT p from Product as p JOIN Shop AS s on s.id = ?1")
    List<Product> getProductsByShopId(Long id);
}
