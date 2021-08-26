package application.supermarket.service;

import application.supermarket.model.entity.Product;

public interface ProductService {
    void addProduct(Product product);

    Product getProductByName(String productName);
}
