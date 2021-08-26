package application.supermarket.service.impl;

import application.supermarket.model.entity.Product;
import application.supermarket.repository.ProductRepository;
import application.supermarket.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProductByName(String productName) {
        return productRepository.getByName(productName);
    }

    @Override
    public List<Product> getProductsByShopId(Long id) {
        return productRepository.getProductsByShopId(id);
    }
}
