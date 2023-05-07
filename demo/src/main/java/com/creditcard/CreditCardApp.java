package com.creditcard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.Arrays;

//help me import the right files and the other java files in the same directory




public class CreditCardApp {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a filename as argument");
            System.exit(-1);
        }
        String filename = args[0];
        FileReaderFactory factory = new FileReaderFactory();
        IFileReader reader = factory.createReader(filename);
        ArrayList<String[]> d = reader.readFile();
        for (String[] row : d) {
            System.out.println(Arrays.toString(row));
        }
    }

}