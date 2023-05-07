package src;

public class CCFactoryUtil {
    public static CCFactory getFactory(String cardNum){
        if (cardNum.startsWith("5")) {
            return new MasterCardFactory();
        } else if (cardNum.startsWith("4")) {
            return new VisaCardFactory();
        } else if (cardNum.startsWith("3")) {
            return new AmexCardFactory();
        } else if (cardNum.startsWith("6011")) {
            return new DiscoverCardFactory();
        } else {
            return new UnknownCardFactory();
        }
    }
}


