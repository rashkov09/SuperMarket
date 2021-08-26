package application.supermarket.controller;

import application.supermarket.model.entity.*;
import application.supermarket.service.*;
import application.supermarket.utils.ValidationUtil;
import application.supermarket.utils.messages.Messages;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
                case 5 -> addProduct();
                case 6 -> setManager();
                case 7 -> distributeProduct();
                case 8 -> getSellersByShop();
                case 9 -> showAllProductsInSpecificShop();

                default -> {
                    System.out.println("Incorrect command, please try again!");
                }
            }
        }

    }

    private void showAllProductsInSpecificShop() throws IOException {
        System.out.println("Enter shop name:");
        Shop shop = shopService.getShopByName(reader.readLine());
        if (shop != null){
        productService.getProductsByShopId(shop.getId()).stream().map(Product::toString).forEach(System.out::println);

        }
    }

    private void getSellersByShop() throws IOException {
        System.out.println("Enter shop name:");
        String shopName = reader.readLine();
        Shop shop = shopService.getShopByName(shopName);
        if (shop != null){
            sellerService.getSellersByShopId(shop.getId()).stream().map(Seller::toString).forEach(System.out::println);
        } else {
            System.out.println("No such shop");
        }
    }

    private void distributeProduct() throws IOException {
        System.out.println("Enter product name:");
        String productName = reader.readLine();
        Product product = productService.getProductByName(productName);
        System.out.println("Enter product distribution in Shops names in format [shopName1 shopName2 ...] :");
        String[] shopNames = reader.readLine().split("\\s+");
        Arrays.stream(shopNames).forEach(shopName -> {
            product.getShops().add(shopService.getShopByName(shopName));
        });
        productService.addProduct(product);
        System.out.println("Successfully added product distribution!");
    }

    private void setManager() throws IOException {
        System.out.println("Enter seller first and last names:");
        String[] input = reader.readLine().split("\\s+");
        Seller seller = sellerService.getByFirstNameAndLastName(input[0],input[1]);
        System.out.println("Enter manager first and last names:");
        input = reader.readLine().split("\\s+");
        Seller manager = sellerService.getByFirstNameAndLastName(input[0],input[1]);
        if (seller != null && manager != null) {
            seller.setManager(manager);
            sellerService.addSeller(seller);
            System.out.printf(Messages.ADDED_SUCCESSFULLY+"%n", "manager");
        } else {
          System.out.println(seller == null ? "No such seller" : "No such manager");
        }

    }

    private void addProduct() throws IOException {
        Product product = new Product();
        System.out.println("Enter product details in format: name price bestBefore(dd-MM-yyyy) category");
        String[] input = reader.readLine().split("\\s+");
        product.setName(input[0]);
        product.setPrice(BigDecimal.valueOf(Long.parseLong(input[1])));
        int[] dateData = Arrays.stream(input[2].split("-")).mapToInt(Integer::parseInt).toArray();
        product.setBestBefore(LocalDate.of(dateData[2],dateData[1],dateData[0]));
        Category category = categoryService.getByName(input[3]);
        product.setCategory(category);

        if (validationUtil.isValid(product)){
            productService.addProduct(product);
            System.out.printf((Messages.ADDED_SUCCESSFULLY) + "%n", "product");
        } else {
            System.out.println(validationUtil.getViolations(product));
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
            System.out.printf((Messages.ADDED_SUCCESSFULLY) + "%n", "seller");
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
            System.out.printf((Messages.ADDED_SUCCESSFULLY) + "%n", "shop");
        } else {
            System.out.println(validationUtil.getViolations(shop));

        }
    }

    private void addTown() throws IOException {
        Town town = new Town();
        System.out.println("Enter town name:");
        town.setName(reader.readLine());
        if(townService.addTown(town) != null){
            System.out.printf((Messages.ADDED_SUCCESSFULLY) + "%n", "town");
        };

    }

    private void addCategory() throws IOException {
        Category category = new Category();
       System.out.println("Enter category name:");
       String categoryName = reader.readLine();
       category.setName(categoryName);
       if (validationUtil.isValid(category)){
           categoryService.addCategory(category);
           System.out.printf((Messages.ADDED_SUCCESSFULLY) + "%n", "category");
       } else {
          System.out.println(validationUtil.getViolations(category));
       }

    }
}
