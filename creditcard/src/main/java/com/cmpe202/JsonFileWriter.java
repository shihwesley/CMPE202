package com.cmpe202;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonFileWriter implements IFileWriter {
    private final String extension;
    private final String filename;

    public JsonFileWriter(String extension) {
        this.extension = extension;
        this.filename = "output_file-2." + extension;

    }
    public void writeFile(ArrayList<CreditCard> creditCards) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        ArrayNode cardsNode = rootNode.putArray("cards");
        for (CreditCard creditCard : creditCards) {
            ObjectNode cardNode = cardsNode.addObject();
            cardNode.put("cardNumber", creditCard.getCardNum() != "" ? creditCard.getCardNum() : "null");
            cardNode.put("cardType", creditCard.getCardType());
        }
        rootNode.set("cards", cardsNode);
        File file = new File(filename);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
    }
}