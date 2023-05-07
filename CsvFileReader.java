import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileReader implements FileReader {
    private final String filename;
    public final ArrayList<String[]> records;
    public CsvFileReader(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    public ArrayList<String[]> readfile() throws IOException {
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
    }
    public ArrayList<String[]> getRecords() {
        return records;
    }
}
