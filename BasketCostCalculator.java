package com.rbc.testexercise;

public class BasketCostCalculator {
	public static void main(String[] args) {
		
	  System.out.println(BasketCostDecorator.calculate(new Basket(),Calculable::withApple (calculable,5), Calculable::withOrange (calculable,4),Calculable::withWaterMelon(calculable,3));
	}
}
