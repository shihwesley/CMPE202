package com.creditcard;

import java.io.IOException;
import java.util.ArrayList;

public interface IFileReader {
    public ArrayList<String[]> readFile() throws IOException;
}