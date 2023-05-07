package src;

public class VisaCard extends CreditCard {
    public VisaCard(String cardNum, CreditCardApp outer) {
        super(cardNum);
        if(isValidCardNumber()) {
            setCardType("Visa");
        } else {
            setCardType("Invalid: not a possible card number");
        }
    }

    @Override
    public boolean isValidCardNumber() {
        boolean isValid = getCardNum().matches("^4\\d{12}(?:\\d{3})?$");
        if (isValid) {
            setCardType("Visa");
        } else {
            setCardType("Invalid: not a possible card number");
        }
        return isValid;
    }
}

