package org.kimbs.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.demo.model.Coffee;
import org.kimbs.demo.model.CoffeeMenu;
import org.kimbs.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CoffeeMachine implements Machine<Coffee, CoffeeMenu> {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Override
    public int getPrice(CoffeeMenu menu) {
        return menu.getPrice();
    }

    @Override
    public CompletableFuture<Coffee> getItem(CoffeeMenu menu) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Make a coffee: {}", menu.getName());
            return coffeeRepository.getCoffee(menu);
        });
    }

    @Override
    public CompletableFuture<List<Coffee>> getItems(CoffeeMenu... menus) {
        List<Coffee> coffees = new ArrayList<>();
        return CompletableFuture.supplyAsync(() -> {
            for (CoffeeMenu menu : menus) {
                log.info("Make a coffee: {}", menu.getName());
                coffees.add(coffeeRepository.getCoffee(menu));
            }
            return coffees;
        });
    }
}
