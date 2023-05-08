package com.cmpe202;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CCFactoryUtil {
    public static CCFactory getFactory(String cardNum){
        
        if (cardNum.matches("^5[1-5]\\d{14}$")) {
            return new MasterCardFactory();
        } else if (cardNum.matches("^4\\d{12}(?:\\d{3})?$")) {
            return new VisaCardFactory();
        } else if (cardNum.matches("^3[47]\\d{13}$")) {
            return new AmexCardFactory();
        } else if (cardNum.matches("^6011\\d{12}$")) {
            return new DiscoverCardFactory();
        } else {
            return new UnknownCardFactory();
        }
    }
}


