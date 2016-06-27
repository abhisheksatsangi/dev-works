package com.rbc.testexercise;

import java.math.BigDecimal;


/**
 * Returns the cost of an individual fruit items expressed as a BigDecimal. 
 * The cost of the individual fruits within the basket is returned via the means
 * of static methods defined in the interface. The cost of individual fruit items
 * have been assigned arbitrarily, for example apples have a cost of 1 whereas Bananas
 * have a cost of 10.The static methods simply implement the interface method for
 * calculating the cost of an individual fruit item.
 * 
 * @param  Calculable  a calculable instance.
 * @return the cost, expressed as a BigDecimal, of the individual fruit item.    
 * 
 */


public interface Calculable {
	
	 BigDecimal calculateCost();
	 
	 static Calculable withApple(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost() {
	                return calculable.calculateCost().add(BigDecimal.ONE);
	            }
	        };
	  }

	 static Calculable withBanana(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost() {
	                return calculable.calculateCost().add(BigDecimal.TEN);
	            }
	        };
	  }
	 
	 static Calculable withOrange(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost() {
	                return calculable.calculateCost().add(BigDecimal.ONE);
	            }
	        };
	  }
	 
	 static Calculable withLemon(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost() {
	                return calculable.calculateCost().add(BigDecimal.TEN);
	            }
	        };
	  }
	 
	 static Calculable withPeach(Calculable calculable) {
	        return new Calculable() {
	            @Override
	            public BigDecimal calculateCost() {
	                return calculable.calculateCost().add(BigDecimal.TEN);
	            }
	        };
	  }
	 


}
