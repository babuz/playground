package dev.learning.playground.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    //Test Deposit

    @Test
    public void test_deposit_open() {
        Account account = new Account();
        account.accountIsVerified();
        account.deposit(new BigDecimal(10));
        account.deposit(new BigDecimal(1));
        assertEquals(11, account.getBalance().longValue());
    }

    @Test
    public void test_deposit_closed() {
        Account account = new Account();
        account.accountIsVerified();
        account.deposit(new BigDecimal(10));
        account.closeAccount();
        account.deposit(new BigDecimal(1));
        assertEquals(10, account.getBalance().longValue());
    }

    @Test
    public void test_NotVerified_CanDepositAmmount_butNotWithdraw() {
        Account account = new Account();
        account.deposit(new BigDecimal(10));
        account.deposit(new BigDecimal(1));
        assertEquals(11, account.getBalance().longValue());

        account.withdraw(new BigDecimal(1));
        assertEquals(11, account.getBalance().longValue());
    }

    @Test
    public void test_Verified_CanDepositAmmount_AndWithdraw() {
        Account account = new Account();
        account.accountIsVerified();
        account.deposit(new BigDecimal(10));
        account.deposit(new BigDecimal(1));
        assertEquals(11, account.getBalance().longValue());

        account.withdraw(new BigDecimal(1));
        assertEquals(10, account.getBalance().longValue());
    }

    @Test
    public void test_FreezedAccount_UnfreezedAfterDeposit() {
        Account account = new Account();
        account.accountIsVerified();
        account.freezeAccount();
        assertTrue(account.isFreezed());
        account.deposit(new BigDecimal(10));
        assertFalse(account.isFreezed());

    }

    @Test
    public void test_FreezedAccount_UnfreezedAfterWithdraw() {
        Account account = new Account();
        account.accountIsVerified();
        account.freezeAccount();
        assertTrue(account.isFreezed());
        account.withdraw(new BigDecimal(1));
        assertFalse(account.isFreezed());

    }
}