package application.supermarket.service;

import application.supermarket.model.entity.Town;

public interface TownService {
    Town addTown(Town town);

    Town getTownByName(String s);
}
