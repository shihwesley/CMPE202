package com.cmpe202;

public class UnknownCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum) {
        return new UnknownCard(cardNum);
    }
}