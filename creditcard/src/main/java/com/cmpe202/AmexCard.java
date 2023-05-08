package com.cmpe202;

public class AmexCard extends CreditCard {
    public AmexCard(String cardNum) {
        super(cardNum);
    }

    @Override
    public String getCardType() {
        return "AmericanExpress";
    }
}
