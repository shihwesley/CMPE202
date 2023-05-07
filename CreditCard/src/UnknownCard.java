package src;



public class UnknownCard extends CreditCard {
    public UnknownCard(String cardNum, CreditCardApp outer) {
        super(cardNum);
        if(isValidCardNumber()) {
            setCardType("not a possible card number");
        } else {
            setCardType("Invalid: not a possible card number");
        } 
    }

    @Override
    public boolean isValidCardNumber() {
        boolean isValid = getCardNum().matches("^6011\\d{12}$");
        if (isValid) {
            setCardType("not a possible card number");
        } else {
            setCardType("Invalid: not a possible card number");
        }
        return isValid;
    }
}