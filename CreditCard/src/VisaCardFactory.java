package src;

public class VisaCardFactory implements CCFactory {
    public CreditCard createCC(String cardNum, CreditCardApp app) {
        return new VisaCard(cardNum, app);
    }
}