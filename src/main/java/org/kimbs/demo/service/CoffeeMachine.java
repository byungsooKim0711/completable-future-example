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
            log.info("Try making a coffee: {}", menu);
            return coffeeRepository.getCoffee(menu);
        }).thenApplyAsync(successMenu -> {
            log.info("Success make a coffee: {}", successMenu);
            return successMenu;
        }).exceptionally(exception -> {
            log.error("Fail making a coffee", exception);
            throw new RuntimeException(exception);
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
        }).thenApplyAsync(coffeeList -> {
            log.info("[SUCCESS] COFFEES: {}", coffeeList.toString());
            return coffeeList;
        }).exceptionally(exception -> {
            log.error("[FAIL] EXCEPTION: {}", exception.getMessage(), exception);
            throw new RuntimeException(exception);
        });
    }
}
