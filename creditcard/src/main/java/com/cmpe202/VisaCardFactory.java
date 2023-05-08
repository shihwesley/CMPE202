package com.cmpe202;

public class VisaCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum) {
        return new VisaCard(cardNum);
    }
}