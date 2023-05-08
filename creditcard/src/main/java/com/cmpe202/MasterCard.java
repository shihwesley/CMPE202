package com.cmpe202;

public class MasterCard extends CreditCard {
    public MasterCard(String cardNum) {
        super(cardNum);
    }

    @Override
    public String getCardType() {
        return "MasterCard";
    }
}
