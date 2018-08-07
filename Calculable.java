package com.bnp.testexercise;

import java.math.BigDecimal;


/**
 * Returns the cost of an individual fruit items expressed as a BigDecimal. 
 * The cost of the individual fruits within the basket is returned via the means
 * of static methods defined in the interface. The cost of individual fruit items
 * have been assigned as per the test instructions, for example apples have a cost of 20 pence whereas Oranges
 * have a cost of 50p.The static methods simply implement the interface method for
 * calculating the cost of an individual fruit item.
 * 
 * @param  Calculable  a calculable instance.
 * @return the cost, expressed as a BigDecimal, of the individual fruit item.    
 * 
 */


public interface Calculable {
	
	 BigDecimal calculateCost(int quantity);
	 
	 static Calculable withApple(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost(int quantity) {
	                return calculable.calculateCost().add(new BigDecimal(".20") * quantity);
	            }
	        };
	  }

	 
	 static Calculable withOrange(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost(int quantity) {
	                return calculable.calculateCost().add(new BigDecimal(".5") * quantity);
	            }
	        };
	  }
	 
	 static Calculable withWaterMelon(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost(int quantity) {
	                return calculable.calculateCost().add(new BigDecimal(".8") * quantity);
	            }
	        };
	  }
	 
}
