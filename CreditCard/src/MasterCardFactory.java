package src;

public class MasterCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum, CreditCardApp app) {
        return new MasterCard(cardNum, app);
    }
}