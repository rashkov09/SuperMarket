package application.supermarket.service;

import application.supermarket.model.entity.Category;

public interface CategoryService {
    void addCategory(Category category);

    Category getByName(String name);
}
