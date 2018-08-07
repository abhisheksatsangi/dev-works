package com.bnp.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.bnp.testexercise.Basket;
import com.bnp.testexercise.BasketCostDecorator;
import com.bnp.testexercise.Calculable;

public class BasketCostCalculatorTest {

	@Test
	public void testFullBasket() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withApple,Calculable::withOrange,Calculable::withWaterMelon),new BigDecimal(32));
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
	public void testBasketWithOnlyWaterMelons() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withWaterMelon),BigDecimal.TEN);
	}
	
	
	@Test
	public void testBasketWithOrangesAndApples() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withOrange,Calculable::withApples),new BigDecimal(11));
	}
	
	@Test
	public void testBasketWithOrangesAndWaterMelons() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withOrange,Calculable::withWaterMelons),new BigDecimal(11));
	}
	
	@Test
	public void testBasketWithApplesAndWaterMelons() {
		assertEquals(BasketCostDecorator.calculate(new Basket(),Calculable::withApples,Calculable::withWaterMelons),new BigDecimal(2));
	}


}
