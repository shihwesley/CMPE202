package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvFileReader implements IFileReader {
    private final String filename;
    public final String fileExtension;
    public final ArrayList<String[]> records;

    public CsvFileReader(String filename) {
        this.filename = filename;
        this.fileExtension = ".csv";
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
        }

        return records;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public ArrayList<String[]> getRecords() {
        return records;
    }
}