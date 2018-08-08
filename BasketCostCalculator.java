package com.bnp.testexercise;
import com.bnp.testexercise.Calculable;

public class BasketCostCalculator {
	
	public static void main(String[] args) {
		
	  Calculable calculable;
	  // test with 4 apples, 3 oranges and 5 water melons.
      System.out.println(BasketCostDecorator.calculate(calculable,Calculable.withApple(calculable,4),Calculable.withOrange(calculable,3),Calculable.withWaterMelon(calculable,5)));

		}
	
}
