package org.kimbs.demo.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum CoffeeMenu {

    ESPRESSO(1200), LATTE(1700), CAPPUCCINO(1600), AMERICANO(1400), MOCHA(2000), AFFOGATO(2200);

    private int price;

    private CoffeeMenu(int price) {
        this.price = price;
    }

    private static Map<String, CoffeeMenu> valueToTypeMap = new HashMap<>();

    static {
        EnumSet.allOf(CoffeeMenu.class).stream().forEach(menu -> valueToTypeMap.put(menu.name(), menu));
    }

    public static Optional<CoffeeMenu> fromValue(String name) {
        return Optional.ofNullable(valueToTypeMap.get(name));
    }

    public String getName() {
        return name();
    }

    public int getPrice() {
        return price;
    }
}
