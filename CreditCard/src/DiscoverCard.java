package src;

public class DiscoverCard extends CreditCard {
    public DiscoverCard(String cardNum, CreditCardApp outer) {
    super(cardNum);
        if(isValidCardNumber()) {
            setCardType("Discover");
        } else {
            setCardType("Invalid: not a possible card number");
        }
    }

    @Override
    public boolean isValidCardNumber() {
        boolean isValid = getCardNum().matches("^6011\\d{12}$");
        if (isValid) {
            setCardType("Discover");
        } else {
            setCardType("Invalid: not a possible card number");
        }
        return isValid;
    }
}
