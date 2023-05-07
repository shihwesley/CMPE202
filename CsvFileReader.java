package com.creditcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;


public class CsvFileReader implements IFileReader {
    private final String filename;
    public final ArrayList<String[]> records;
    public CsvFileReader(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    public ArrayList<String[]> readFile() throws IOException {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                records.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
    public ArrayList<String[]> getRecords() {
        return records;
    }
}
