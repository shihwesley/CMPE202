package com.cmpe202;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvFileReader implements IFileReader {
    private final String filename;
    public final String fileExtension;
    public final ArrayList<CreditCard> creditcards;

    public CsvFileReader(String filename) {
        this.filename = filename;
        this.fileExtension = ".csv";
        this.creditcards= new ArrayList<CreditCard>();
    }

    public ArrayList<CreditCard> readFile() throws IOException {
        File file = new File(filename);
        ArrayList<CreditCard> creditcards = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String cardNum = data[0];
                //String expirationDate = data[1];
                //String nameOfCardHolder = data[3];
                CCFactory Factory = CCFactoryUtil.getFactory(cardNum);
                CreditCard cc = Factory.createCC(cardNum);
                //cc.setCardExpirationDate(expirationDate);
                //cc.setNameOfCardHolder(nameOfCardHolder);
                creditcards.add(cc);
            }
        }
        return creditcards;
    }

    public ArrayList<CreditCard> getRecords() {
        return creditcards;
    }
 
}