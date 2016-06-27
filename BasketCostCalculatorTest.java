package com.rbc.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.rbc.testexercise.Basket;
import com.rbc.testexercise.BasketCostDecorator;
import com.rbc.testexercise.Calculable;

public class BasketCostCalculatorTest {

	@Test
	public void testFullBasket() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withApple, Calculable::withBanana,Calculable::withOrange,Calculable::withPeach,Calculable::withLemon),new BigDecimal(32));
	}
	
	@Test
	public void testBasketWithOnlyApples() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withApple),BigDecimal.ONE);
	}
	
	@Test
	public void testBasketWithOnlyOranges() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withOrange),BigDecimal.ONE);
	}
	
	@Test
	public void testBasketWithOnlyPeaches() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withPeach),BigDecimal.TEN);
	}
	
	@Test
	public void testBasketWithOnlyBananas() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withBanana),BigDecimal.TEN);
	}
	
	@Test
	public void testBasketWithOnlyLemons() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withLemon),BigDecimal.TEN);
	}
	
	@Test
	public void testBasketWithOrangesAndPeaches() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withOrange,Calculable::withPeach),new BigDecimal(11));
	}
	
	@Test
	public void testBasketWithOrangesAndLemons() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withOrange,Calculable::withLemon),new BigDecimal(11));
	}
	
	@Test
	public void testBasketWithOrangesAndApples() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withOrange,Calculable::withApple),new BigDecimal(2));
	}

	
	@Test
	public void testBasketWithBananasOrangesAndApples() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withBanana,Calculable::withOrange,Calculable::withApple),new BigDecimal(12));
	}
	
	@Test
	public void testBasketWithPeachesOrangesAndApples() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withPeach,Calculable::withOrange,Calculable::withApple),new BigDecimal(12));
	}
	
	@Test
	public void testBasketWithLemonOrangesAndApples() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withLemon,Calculable::withOrange,Calculable::withApple),new BigDecimal(12));
	}

}
