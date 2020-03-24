package org.kimbs.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kimbs.demo.model.Coffee;
import org.kimbs.demo.model.CoffeeMenu;
import org.kimbs.demo.service.CoffeeMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

//@SpringBootTest
class CompletableFutureApplicationTests {

	@Autowired
	private CoffeeMachine machine;

	@Test
	@DisplayName("테스트")
	void test() throws Exception {
		Coffee coffee = machine.getItem(CoffeeMenu.LATTE).get();

		Assertions.assertEquals("LATTE", coffee.getName());
		Assertions.assertEquals(1700, coffee.getPrice());
	}

	@Test
    void test1() throws Exception {
		CompletableFuture<Coffee> mocha = machine.getItem(CoffeeMenu.MOCHA);
		CompletableFuture<Coffee> latte = machine.getItem(CoffeeMenu.LATTE);
		CompletableFuture<Coffee> americano = machine.getItem(CoffeeMenu.AMERICANO);

		List<CompletableFuture<Coffee>> futureCoffees = Arrays.asList(mocha, latte, americano);

		List<Coffee> coffees = CompletableFuture.allOf(futureCoffees.toArray(new CompletableFuture[futureCoffees.size()]))
				.thenApply(Void -> futureCoffees.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();

		assertThat(coffees).contains(
				Coffee.builder().name("MOCHA").price(2000).build(),
				Coffee.builder().name("LATTE").price(1700).build(),
				Coffee.builder().name("AMERICANO").price(1400).build()
		);
    }
}
