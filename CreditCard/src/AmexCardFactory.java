package src;

public class AmexCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum, CreditCardApp app) {
        return new AmexCard(cardNum, app);
    }
}