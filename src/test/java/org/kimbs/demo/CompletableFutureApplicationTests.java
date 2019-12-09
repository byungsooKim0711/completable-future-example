package org.kimbs.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kimbs.demo.model.Coffee;
import org.kimbs.demo.model.CoffeeMenu;
import org.kimbs.demo.service.CoffeeMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
}
