package src;

public class MasterCard extends CreditCard {
    public MasterCard(String cardNum, CreditCardApp outer) {
        super(cardNum);
        if(isValidCardNumber()) {
            setCardType("MasterCard");
        } else {
            setCardType("Invalid: not a possible card number");
        }
    }

    @Override
    public boolean isValidCardNumber() {
        boolean isValid = getCardNum().matches("^5[1-5]\\d{14}$");
        if (isValid) {
            setCardType("MasterCard");
        }
        return isValid;
    }
}
