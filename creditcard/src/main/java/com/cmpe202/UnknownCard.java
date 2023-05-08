package com.cmpe202;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UnknownCard extends CreditCard {
    public UnknownCard(String cardNum) {
        super(cardNum);
        setCardType(checkNumber(cardNum));
    }

    public String checkNumber(String cardNum) {
       String regex = "^(?!.{20})(?:\\d+)?$";
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(cardNum);
       if (cardNum.isEmpty()) 
       {
            return "Invalid: empty/null card number";
       } else if (cardNum.length() > 19) {
            return "Invalid: more than 19 digits";
       } else if (!matcher.matches()) {
            return "Invalid: non numeric characters";
       } else {
           return "Invalid: not a possible card number";
       }
    }
}