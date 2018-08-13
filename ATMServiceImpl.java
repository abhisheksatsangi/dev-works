package com.ModularTest.atm;
import com.ModularTest.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.*;


public class ATMServiceImpl {

    private final AccountService AccountService;
    private final Map<Integer, Integer> atmNotes = new HashMap<>();
    private static final Integer MIN_AMOUNT_THAT_CAN_BE_WITHDRAWN = 20;
    private static final Integer MAX_AMOUNT_THAT_CAN_BE_WITHDRAWN = 250;
    private static final Logger LOG = LoggerFactory.getLogger(ATMServiceImpl.class);



    public ATMServiceImpl(AccountService AccountService) {
        this.AccountService = AccountService;
    }



    void replenish() {

    	atmNotes.put(5, 20);
    	atmNotes.put(10, 20);
        atmNotes.put(20, 20);
        atmNotes.put(50, 20);
    }



    public synchronized String checkBalance(int accountId) {

        return AccountService.checkBalance(accountId);

    }



    /**

     * @param accountId

     * @param amount

     * @return @{code List}of {@code Integer}(bank notes)

     * @throws IllegalArgumentException when the amount is less than 20

     * @throws IllegalArgumentException when the amount is more than 250

     * @throws IllegalArgumentException when the amount is not dividable by 5

     */

    public synchronized List<Integer> withdrawAmount(int accountId, Integer amount) {

        executeBoundaryValidation(amount);

        List<Integer> bankNotes = getATMNotes(amount);

        AccountService.withdrawAmount(accountId, BigDecimal.valueOf(amount));

        LOG.info("Handing out: amount={} accountId={}", amount, accountId);

        return bankNotes;

    }



    // visible for testing

    Map<Integer, Integer> getATMNotes() {

        return atmNotes;

    }



    private List<Integer> getATMNotes(Integer amount) {

        List<Integer> fiveNotesFirst = dispenseFiveNotesFirst(amount, new HashMap<>(atmNotes));

        if (!fiveNotesFirst.isEmpty()) {
        	takeAwayOneNoteFromMap(fiveNotesFirst);
            return fiveNotesFirst;
        }



        LOG.warn("The system tried to handout money with 5 bank note in it, but failed");

        List<Integer> notesWithoutFiveNotes = lookForAllNotes(amount, new ArrayList<>(), new HashMap<>(atmNotes));

        if (!notesWithoutFiveNotes.isEmpty()) {

        	takeAwayOneNoteFromMap(notesWithoutFiveNotes);

            return notesWithoutFiveNotes;

        } else {

            LOG.error("Could not hand out amount={}, because there are not enough bank notes", amount);

            throw new IllegalStateException("Not enough bank notes");

        }

    }



    private void takeAwayOneNoteFromMap(List<Integer> notes) {

        for (int note : notes) {

            atmNotes.put(note, atmNotes.get(note) - 1);

        }

    }



    private List<Integer> dispenseFiveNotesFirst(Integer amount, Map<Integer, Integer> copyOfAtmNotes) {

        List<Integer> notes = new ArrayList<>();

        removeNoteFromMap(copyOfAtmNotes, notes, 5);

        return lookForAllNotes(amount - 5, notes, copyOfAtmNotes);

    }



    private List<Integer> lookForAllNotes(Integer amount, List<Integer> notes, Map<Integer, Integer> copyOfAtmNotes) {
        for (int note : sortInDescendingOrder(copyOfAtmNotes.keySet())) {
            if (amount >= note && copyOfAtmNotes.get(note) > 0) {
            	removeNoteFromMap(copyOfAtmNotes, notes, note);
                return lookForAllNotes(amount - note, notes, copyOfAtmNotes);
            }
        }
        return amount == 0 ? notes : new ArrayList<>();
    }



    private void removeNoteFromMap(Map<Integer, Integer> bankNotesCopy, List<Integer> notes, int note) {
        bankNotesCopy.put(note, bankNotesCopy.get(note) - 1);
        notes.add(note);
    }



    private List<Integer> sortInDescendingOrder(Set<Integer> keysSet) {

        List<Integer> keys = new ArrayList<>(keysSet);

        keys.sort(Comparator.reverseOrder());

        return keys;

    }



    private void executeBoundaryValidation(Integer amount) {

        if (amount < MIN_AMOUNT_THAT_CAN_BE_WITHDRAWN) {

            LOG.warn("Withdrawals less than 20 not possible");

            throw new IllegalArgumentException("Withdrawals less than 20 are not allowed");

        }

        if (amount > MAX_AMOUNT_THAT_CAN_BE_WITHDRAWN) {

            LOG.warn("Withdrawals greater than 250 not possible");

            throw new IllegalArgumentException("Withdrawals greater than 250 are not allowed");

        }

        if (amount % 5 != 0) {

            LOG.warn("User tried to get amount which was not divisible by 5");

            throw new IllegalArgumentException("Sorry, the ATM cannot hand out " + amount + " as it is not divisible by 5");

        }

    }
}
