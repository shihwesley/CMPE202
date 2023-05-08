package com.cmpe202;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;


public class CreditCardApp {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a filename as argument");
            System.exit(-1);
        }
        String filename = args[0];

        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        FormatFactory factory = null;
        switch(extension) {
            case "csv":
                factory = new CsvFactory();
                break;
            case "json":
                factory = new JsonFactory();
                break;
            case "xml":
                factory = new XmlFactory();
                break;
            default:
                System.out.println("Unknown file type: " + filename);
        }
        IFileReader reader = factory.createReader(filename);
        IFileWriter writer = factory.createWriter(extension);

        try {
            ArrayList<CreditCard> creditCards = new ArrayList<>();
            creditCards = reader.readFile();
            writer.writeFile(creditCards);
        }  catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

    }   
}