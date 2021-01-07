package dev.learning.playground.domain;

import java.math.BigDecimal;

public class Account {
    //    Business Rules
//	- you can withdraw amount if the user has been verified and the account is not closed
//	- you can deposit the amount if the account is not closed
//	- you can freeze account if the account holder is not verified and the account is not closed
//	- deposit or withdraw will unfreeze the account
//
    private BigDecimal balance;
    private boolean isVerified;
    private boolean isClosed;
    private boolean freeze;

    public boolean isVerified() {
        return isVerified;
    }

    public void closeAccount() {
        this.isClosed = true;
    }

    public void freezeAccount() {
        if (isClosed | isVerified)
            return;
        this.freeze = true;
    }

    public void accountVerified() {
        isVerified = true;
    }


    public Account() {
        this.balance = BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        if (isClosed)
            return;
        unfreeze();
        this.balance.add(amount);
    }

    private void unfreeze() {
        if (freeze) {
            this.freeze = false;
        }
    }

    public void withdraw(BigDecimal amount) {
        if (!isVerified || isClosed)
            return;

        unfreeze();

        if (balance.compareTo(amount) > 0) {
            balance.subtract(amount);
        }
    }
}
