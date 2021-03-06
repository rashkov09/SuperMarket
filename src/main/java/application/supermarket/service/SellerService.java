package application.supermarket.service;

import application.supermarket.model.entity.Seller;

import java.util.List;

public interface SellerService {
    void addSeller(Seller seller);

    Seller getByFirstNameAndLastName(String firstName, String lastName);

    List<Seller> getSellersByShopId(Long id);
}
