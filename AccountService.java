package com.ModularTest.service;
import java.math.BigDecimal;

public interface AccountService {
	    String checkBalance(int accountId);
	    boolean withdrawAmount(int accountId, BigDecimal amount);
}


