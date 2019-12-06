package org.kimbs.demo.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Coffee {
    private String name;
    private int price;
}
