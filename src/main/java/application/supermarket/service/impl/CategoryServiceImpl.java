package application.supermarket.service.impl;

import application.supermarket.model.entity.Category;
import application.supermarket.repository.CategoryRepository;
import application.supermarket.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.getByName(name);
    }
}
