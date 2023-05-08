package com.cmpe202;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonFileReader implements IFileReader {
    private final String filename;
    public final String fileExtension;
    public final ArrayList<CreditCard> creditcards;

    public JsonFileReader(String filename) {
        this.filename = filename;
        this.fileExtension = ".json";
        this.creditcards= new ArrayList<CreditCard>();
    }

    public ArrayList<CreditCard> readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<CreditCard> creditcards = new ArrayList<CreditCard>();
        File file = new File(filename);
        try {
            JsonNode rootNode = mapper.readTree(file);
            JsonNode cardsNode = rootNode.get("cards");
            String cardNumber = "";
            for (JsonNode cardNode : cardsNode) {
                JsonNode node = cardNode.get("cardNumber");
                if (node != null) {
                    cardNumber = node.asText();
                } else {
                    cardNumber = "";
                }
                String expirationDate = cardNode.get("expirationDate").asText();
                String nameOfCardHolder = cardNode.get("cardHolderName").asText();
                CCFactory Factory = CCFactoryUtil.getFactory(cardNumber);
                CreditCard cc = Factory.createCC(cardNumber);
                cc.setCardExpirationDate(expirationDate);
                cc.setNameOfCardHolder(nameOfCardHolder);
                creditcards.add(cc);
            }
            return creditcards;
        } catch (JsonMappingException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return creditcards;
    }
}
