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
    private boolean isFreezed;
    //private EnsureUnfrozeen ensureUnfrozeen;

    public Account() {
        this.balance = BigDecimal.ZERO;
        this.isClosed = false;
        this.isVerified = false;
        this.isFreezed = true;
       // this.ensureUnfrozeen = this::stayUnfreeze;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public boolean isVerified() {
        return isVerified;
    }
    public boolean isFreezed() { return isFreezed; }
    public boolean isClosed() { return isClosed; }

    public void closeAccount() {
        this.isClosed = true;
    }
    public void openAccount() { this.isClosed = false; }
    public void accountIsVerified() { this.isVerified = true; }
    public void accountIsNotVerified() { this.isVerified = false; }
    public void unFreezeAccount() { this.isFreezed = false; }


    public void freezeAccount() {
        if (isClosed | isVerified)
            return;
        this.isFreezed = true;
        this.ensureUnfreeze();
        //this.ensureUnfrozeen = this::unfreeze;
    }

    public void accountVerified() {
        isVerified = true;
    }

    public void deposit(BigDecimal amount) {
        if (isClosed)
            return;
        this.ensureUnfreeze();
        this.balance = this.balance.add(amount);
    }


    public void withdraw(BigDecimal amount) {
        if (!isVerified || isClosed)
            return;

        this.ensureUnfreeze();
        if (balance.compareTo(amount) > 0) {
           this.balance =  balance.subtract(amount);
        }
    }


    private void ensureUnfreeze() {
        if (isFreezed) {
            unfreeze();
        } else {
            stayUnfreeze();
        }
    }

    private void unfreeze() {
        this.isFreezed = false;
        //this.ensureUnfrozeen = this::stayUnfreeze;
    }

    private void stayUnfreeze(){

    }
}
