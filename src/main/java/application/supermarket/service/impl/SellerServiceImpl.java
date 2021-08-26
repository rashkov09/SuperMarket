package application.supermarket.service.impl;

import application.supermarket.model.entity.Seller;
import application.supermarket.repository.SellerRepository;
import application.supermarket.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void addSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    @Override
    public Seller getByFirstNameAndLastName(String firstName, String lastName) {
        return sellerRepository.getByFirstNameAndLastName(firstName,lastName);
    }
}
