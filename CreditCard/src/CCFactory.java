package src;

public interface CCFactory {
    CreditCard createCC(String cardNum, CreditCardApp app);
}