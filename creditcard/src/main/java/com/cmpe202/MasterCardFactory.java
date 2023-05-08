package com.cmpe202;

public class MasterCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum) {
        return new MasterCard(cardNum);
    }
}