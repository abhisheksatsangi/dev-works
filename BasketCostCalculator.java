package com.rbc.testexercise;

public class BasketCostCalculator {
	public static void main(String[] args) {
		
	  System.out.println(BasketCostDecorator.calculate(new Basket(),Calculable::withApple, Calculable::withBanana,Calculable::withOrange,Calculable::withPeach,Calculable::withLemon));
	}
}
