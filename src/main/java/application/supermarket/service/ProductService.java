package application.supermarket.service;

import application.supermarket.model.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    Product getProductByName(String productName);

    List<Product> getProductsByShopId(Long id);
}
