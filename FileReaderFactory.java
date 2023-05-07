package com.creditcard;

import java.io.IOException;
import java.io.FileReader;

public class FileReaderFactory {
    public CsvFileReader createReader(String filename) {
        if (filename.endsWith(".csv")) {
            return new CsvFileReader(filename);
        }
        throw new IllegalArgumentException("Unknown file type: " + filename);
    }
}