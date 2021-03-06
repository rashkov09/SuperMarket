package application.supermarket.repository;

import application.supermarket.model.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller getByFirstNameAndLastName(String firstName, String lastName);

    List<Seller> getSellersByShopId(Long id);
}
