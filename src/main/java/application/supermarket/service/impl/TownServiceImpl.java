package application.supermarket.service.impl;

import application.supermarket.model.entity.Town;
import application.supermarket.repository.TownRepository;
import application.supermarket.service.TownService;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {
    private  final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public Town addTown(Town town) {
      return townRepository.save(town);
    }
}
