package com.cmpe202;

public class AmexCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum) {
        return new AmexCard(cardNum);
    }
}