package application.supermarket.controller;

import application.supermarket.model.entity.Category;
import application.supermarket.model.entity.Town;
import application.supermarket.service.*;
import application.supermarket.utils.ValidationUtil;
import application.supermarket.utils.messages.Messages;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final SellerService sellerService;
    private final ShopService shopService;
    private final TownService townService;
    private final BufferedReader reader;
    private final ValidationUtil validationUtil;

    public CommandLineRunnerImpl(CategoryService categoryService, ProductService productService, SellerService sellerService, ShopService shopService, TownService townService, BufferedReader reader, ValidationUtil validationUtil) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.shopService = shopService;
        this.townService = townService;
        this.reader = reader;
        this.validationUtil = validationUtil;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println(Messages.MAIN_MESSAGE);
            int commandNumber = Integer.parseInt(reader.readLine());
            if (commandNumber == 0) {
                System.exit(0);
            }
            switch (commandNumber) {
                case 1 -> addCategory();
                case 2 -> addTown();

                default -> {
                    System.out.println("Incorrect command, please try again!");
                    return;
                }
            }
        }

    }

    private void addTown() throws IOException {
        Town town = new Town();
        System.out.println("Enter town name:");
        town.setName(reader.readLine());
        if(townService.addTown(town) != null){
            System.out.println(Messages.ADDED_TOWN);
        };

    }

    private void addCategory() throws IOException {
        Category category = new Category();
       System.out.println("Enter category name:");
       String categoryName = reader.readLine();
       category.setName(categoryName);
       if (validationUtil.isValid(category)){
           categoryService.addCategory(category);
           System.out.println(Messages.ADDED_CATEGORY);
       } else {
          System.out.println(validationUtil.getViolations(category).stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n")));
       }

    }
}
