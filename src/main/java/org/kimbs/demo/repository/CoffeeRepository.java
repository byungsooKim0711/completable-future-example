package org.kimbs.demo.repository;

import org.kimbs.demo.model.Coffee;
import org.kimbs.demo.model.CoffeeMenu;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CoffeeRepository {

    public Coffee getCoffee(CoffeeMenu menu) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Coffee.builder().name(menu.getName()).price(menu.getPrice()).build();
    }
}
