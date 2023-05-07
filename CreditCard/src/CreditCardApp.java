package src;

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
//import javax.json.*;


public class CreditCardApp {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a filename as argument");
            System.exit(-1);
        }
        String filename = args[0];

        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        System.err.println("Extension: " + extension);
        FormatFactory factory = null;
        switch(extension) {
            case "csv":
                factory = new CsvFactory();
            case "json":
                factory = new JsonFactory();
            //default:
            //    System.out.println("Unknown file type: " + filename);
        }
        IFileReader reader = factory.createReader(filename);
        IFileWriter writer = factory.createWriter(filename, reader);

        try {
            ArrayList<String[]> d = reader.readFile();
            writer.writeFile(d);
        }  catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

    }   
}