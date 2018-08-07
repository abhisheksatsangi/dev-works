package com.bnp.testexercise;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Decorates a basket by applying lambda constructs reduce and andThen on a stream of 
 * instances which can have their costs calculated. In this case, it is the basket of fruits 
 * which are deemed as cost-calculable, but in theory it could be anything that could be cost
 * calculation worthy.
 * @param  calculations - a var-arg of the calculable objects.
 * @return the sum cost, expressed as a BigDecimal, of the calculable objects passed in as a parameter.    
 * 
 */

public class BasketCostDecorator {
	
	private final Function<Calculable, Calculable> totalCost;

	@SafeVarargs
	private BasketCostDecorator(Function<Calculable, Calculable>... calculations) {
	        this.totalCost = Stream.of(calculations)
	                .reduce(Function.identity(), Function::andThen);

	    }

    @SafeVarargs
	public static BigDecimal calculate(Calculable calculable, Function<Calculable, Calculable>... calculations) {
	        return new BasketCostDecorator(calculations).calculate(calculable);
	    }

	private BigDecimal calculate(Calculable calculable) {
	    return this.totalCost.apply(calculable).calculateCost();
	}

}



