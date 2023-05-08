package com.cmpe202;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CsvFileWriter implements IFileWriter {
    private final String extension;

    public CsvFileWriter(String extension) {
        this.extension = extension;
    }
    public void writeFile(ArrayList<CreditCard> creditCards) throws IOException {
        String outputFile = "output_file." + extension;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String header = "cardNumber,cardType";
            writer.write(header + "\n");
            for (int i = 1; i < creditCards.size(); i++) {
                CreditCard card = creditCards.get(i);
                String cardNum = card.getCardNum();
                String cardType = card.getCardType();
                writer.write(cardNum + "," + cardType + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}