package com.cmpe202;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DiscoverCard extends CreditCard {
    public DiscoverCard(String cardNum) {
        super(cardNum);
    }

    @Override
    public String getCardType() {
        return "Discover";
    }
}
