package src;

public class AmexCard extends CreditCard {
    public AmexCard(String cardNum, CreditCardApp outer) {
        super(cardNum);
        if(isValidCardNumber()) {
            setCardType("AmericanExpress");
        } else {
            setCardType("Invalid: not a possible card number");
        }
    }

    @Override
    public boolean isValidCardNumber() {
        boolean isValid = getCardNum().matches("^3[47]\\d{13}$");
        if (isValid) {
            setCardType("AmericanExpress");
        } else {
            setCardType("Invalid: not a possible card number");
        }
        return isValid;
    }
}
