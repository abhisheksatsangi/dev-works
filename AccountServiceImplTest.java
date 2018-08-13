package test.com.ModularTest;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceImplTest {

    private final AccountService accountService = new AccountServiceImpl();

    @Test
    void testCheckBalance() {

        assertThat(accountService.checkBalance(1001)).isEqualTo("2738.59");

    }

    @Test
    @DisplayName("withdraw should subtract the amount from the balance")
    void withDrawAmount_subtractAmountFrom_balance() {

        // given
        int accountId = 1001;
        // when
        accountService.withdrawAmount(accountId, BigDecimal.valueOf(38.59));
        // then
        assertThat(accountService.checkBalance(accountId)).isEqualTo("2700.00");
    }



    @Test
    @DisplayName("When user withdraws negative amount then throw exception")
    void withdrawAmount_throwsException_whenAmountIsNegative() {
        // given
        int accountId = 1001;
        // when
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> accountService.withdrawAmount(accountId, BigDecimal.valueOf(-1)));
        // then
        assertThat(throwable.getMessage()).isEqualTo("The amount cannot be negative");
    }



    @Test
    @DisplayName("An exception is thrown when accountId is not found")
    void withdrawAmount_throwsException_whenAccountIdIsNotFound() {
        // given
        int accountId = 1000;
        // when
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> accountService.withdrawAmount(accountId, BigDecimal.valueOf(2)));
        // then
        assertThat(throwable.getMessage()).isEqualTo("Could not find accountId: 1000");
    }


    @Test
    @DisplayName("When the user withdraws less than funds available on the account, the withdrawal is successful")
    void withdrawAmount_withdrawsLessThanFundsAvailable() {
        // given
        int accountId = 1001;
        // when
        boolean returnValue = accountService.withdrawAmount(accountId, BigDecimal.valueOf(30));
        // then
       assertThat(returnValue).isTrue();
    }



    @Test
    @DisplayName("When the user withdraws more than funds available on the account, the withdrawal is successful")
    void withdrawAmount_withdrawsMoreThanFundsAvailable() {
        // given
        int accountId = 1001;
        // when
        boolean returnValue = accountService.withdrawAmount(accountId, BigDecimal.valueOf(3000));
        // then
        assertThat(returnValue).isFalse();
    }



    @Test
    @DisplayName("checkBalance throws an exception when accountId is not found")
    void checkBalance_throwsException_whenAccountIdIsNotFound() {
        // given
        int accountId = 1000;
        // when
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> accountService.checkBalance(accountId));
        // then
        assertThat(throwable.getMessage()).isEqualTo("Could not find accountId: 1000");
    }

}
public class AccountServiceImplTest {

}
