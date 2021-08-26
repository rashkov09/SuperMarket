package application.supermarket.service.impl;

import application.supermarket.model.entity.Shop;
import application.supermarket.repository.ShopRepository;
import application.supermarket.service.ShopService;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public Shop getShopByName(String name) {
        return shopRepository.getByName(name);
    }
}
