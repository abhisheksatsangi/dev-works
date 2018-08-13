package com.ModularTest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;



public class AccountServiceImpl implements AccountService {

    private final Map<Integer, BigDecimal> accounts = new HashMap<>();

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);



    public AccountServiceImpl() {

    	accounts.put(1001, BigDecimal.valueOf(2738.59));

    	accounts.put(1002, BigDecimal.valueOf(23.00));

    	accounts.put(1003, BigDecimal.valueOf(0.00));

    }



    public String checkBalance(int accountId) {

        if (!accounts.containsKey(accountId)) {

            throw new IllegalArgumentException("Unable to locate accountId: " + accountId);

        }

        return accounts.get(accountId).toPlainString();

    }



    /**

     *

     * @param accountId

     * @param amount

     * @return {@code boolean} whether the transaction was successful or not

     * @throws IllegalArgumentException when the amount is negative

     * @throws IllegalArgumentException when it could not find accountId

     *

     */

    public boolean withdrawAmount(int accountId, BigDecimal amount) {
    	
        if (amount.signum() == -1) {
            LOG.warn("Amount withdrawn cannot be negative {}", amount);
            throw new IllegalArgumentException("The withdrawn amount cannot be negative");

        }



        if (!accounts.containsKey(accountId)) {
            LOG.error("Could not locate accountId={}", accountId);
            throw new IllegalArgumentException("Could not locate accountId: " + accountId);
        }



        BigDecimal balanceAfterWithdrawal = accounts.get(accountId).subtract(amount);

        if (balanceAfterWithdrawal.signum() == -1) {
            LOG.info("Not enough funds in account, accountId={}, amount={}, funds={}", accountId, amount, accounts.get(accountId));
            return false;
        }

        accounts.put(accountId, balanceAfterWithdrawal);

        LOG.info("Withdrawing amount={} from accountId={}", amount, accountId);

        return true;

    }

}
