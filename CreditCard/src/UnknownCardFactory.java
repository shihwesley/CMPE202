package src;

public class UnknownCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum, CreditCardApp app) {
        return new UnknownCard(cardNum, app);
    }
}