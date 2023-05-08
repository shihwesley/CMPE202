package com.cmpe202;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Matcher;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Array;


public abstract class CreditCard {
    @JsonProperty(defaultValue = "")
    private final String cardNum;

    @JsonProperty(defaultValue = "")
    private String cardType;

    @JsonProperty(required = true)
    private String expirationDate;

    @JsonProperty(required = true)
    private String nameOfCardHolder;

    public CreditCard() {
        this.cardNum = "";
    }

    public CreditCard(String cardNum) {
        this.cardNum = cardNum;
        this.cardType = "";
        this.expirationDate = "";
        this.nameOfCardHolder = "";
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardType() {
        return cardType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    protected String setCardType(String cardType) {
        this.cardType = cardType;
        return cardType;
    }

    protected void setCardExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    protected void setNameOfCardHolder(String nameOfCardHolder) {
        this.nameOfCardHolder = nameOfCardHolder;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNum + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cardHolderName='" + nameOfCardHolder + '\'' +
                '}';
    }
}

