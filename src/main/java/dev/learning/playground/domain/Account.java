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
    private EnsureUnfrozeen ensureUnfrozeen;

    public Account(EnsureUnfrozeen ensureUnfrozeen) {
        this.balance = BigDecimal.ZERO;
        this.ensureUnfrozeen = this::stayUnfreeze;
    }





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
        this.ensureUnfrozeen = this::unfreeze;
    }

    public void accountVerified() {
        isVerified = true;
    }




    public void deposit(BigDecimal amount) {
        if (isClosed)
            return;
        this.ensureUnfrozeen.execute();
        this.balance.add(amount);
    }


    public void withdraw(BigDecimal amount) {
        if (!isVerified || isClosed)
            return;

        this.ensureUnfrozeen.execute();

        if (balance.compareTo(amount) > 0) {
            balance.subtract(amount);
        }
    }


    private void ensureUnfreeze() {
        if (freeze) {
            unfreeze();
        } else {
            stayUnfreeze();
        }
    }

    private void unfreeze() {
        this.freeze = false;
        this.ensureUnfrozeen = this::stayUnfreeze;
    }

    private void stayUnfreeze(){

    }
}
