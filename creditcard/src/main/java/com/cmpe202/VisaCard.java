package com.cmpe202;

public class VisaCard extends CreditCard {
    public VisaCard(String cardNum) {
        super(cardNum);
    }

    @Override
    public String getCardType() {
        return "Visa";
    }
}

