package application.supermarket.service;

import application.supermarket.model.entity.Shop;

public interface ShopService {
    void addShop(Shop shop);

    Shop getShopByName(String name);
}
