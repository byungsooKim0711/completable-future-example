package org.kimbs.demo;

import org.kimbs.demo.model.Coffee;
import org.kimbs.demo.model.CoffeeMenu;
import org.kimbs.demo.service.CoffeeMachine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CoffeeController {

    private final CoffeeMachine coffeeMachine;

    public CoffeeController(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @GetMapping("/coffee/{names}")
    public ResponseEntity<?> getCoffee(@PathVariable("names") List<String> names) {
        List<CompletableFuture<Coffee>> coffees = new ArrayList<>();

        names.forEach(name -> {
            CoffeeMenu.fromValue(name.toUpperCase()).ifPresent(menu -> coffees.add(coffeeMachine.getItem(menu)));
        });

        return ResponseEntity.ok(CompletableFuture.allOf(coffees.toArray(new CompletableFuture[coffees.size()]))
                .thenApply(Void -> coffees.stream().map(CompletableFuture::join).collect(Collectors.toList())).join());
    }
}
