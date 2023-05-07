import java.io.IOException;

public class FileReaderFactory {
    public FileReader createReader(String filename) {
        if (filename.endsWith(".csv")) {
            return new CsvFileReader(filename);
        }
        throw new IllegalArgumentException("Unknown file type: " + filename);
    }
}