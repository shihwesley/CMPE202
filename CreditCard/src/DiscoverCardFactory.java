package src;

public class DiscoverCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum, CreditCardApp app) {
        return new DiscoverCard(cardNum, app);
    }
}