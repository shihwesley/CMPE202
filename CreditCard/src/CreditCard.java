package src;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedWriter;
import java.io.FileWriter;

abstract class CreditCard {
    private final String cardNum;
    private String cardType;

    public CreditCard(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardType() {
        return cardType;
    }

    protected void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public abstract boolean isValidCardNumber();
}

