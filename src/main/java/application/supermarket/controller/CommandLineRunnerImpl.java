package application.supermarket.controller;

import application.supermarket.model.entity.Category;
import application.supermarket.model.entity.Seller;
import application.supermarket.model.entity.Shop;
import application.supermarket.model.entity.Town;
import application.supermarket.service.*;
import application.supermarket.utils.ValidationUtil;
import application.supermarket.utils.messages.Messages;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
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
                case 3 -> addShop();
                case 4 -> addSeller();

                default -> {
                    System.out.println("Incorrect command, please try again!");
                }
            }
        }

    }

    private void addSeller() throws IOException {
        Seller seller = new Seller();
        System.out.println("Enter seller details in format: firstName lastName age salary shopName");
        String[] input = reader.readLine().split("\\s+");
        seller.setFirstName(input[0]);
        seller.setLastName(input[1]);
        seller.setAge(Integer.parseInt(input[2]));
        seller.setSalary(BigDecimal.valueOf(Long.parseLong(input[3])));
        Shop shop  = shopService.getShopByName(input[4]);
        seller.setShop(shop);
        if (validationUtil.isValid(seller)){
            sellerService.addSeller(seller);
        }else {
            System.out.println(validationUtil.getViolations(seller));
        }

    }

    private void addShop() throws IOException {
        Shop shop = new Shop();
        System.out.println("Enter shop details in format: name address town");
        String[] input = reader.readLine().split("\\s+");
        shop.setName(input[0]);
        shop.setAddress(input[1]);
        Town town = townService.getTownByName(input[2]);
        shop.setTown(town);
        if (validationUtil.isValid(shop)){
            shopService.addShop(shop);
            System.out.println(Messages.ADDED_SHOP);
        } else {
            System.out.println(validationUtil.getViolations(shop));

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
          System.out.println(validationUtil.getViolations(category));
       }

    }
}
