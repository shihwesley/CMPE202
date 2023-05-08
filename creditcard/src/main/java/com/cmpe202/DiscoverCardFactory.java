package com.cmpe202;

public class DiscoverCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum) {
        return new DiscoverCard(cardNum);
    }
}