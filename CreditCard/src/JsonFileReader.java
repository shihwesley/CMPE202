package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.json.*;

public class JsonFileReader implements IFileReader {
    private final String filename;
    public final String fileExtension;
    public final ArrayList<String[]> records;

    public JsonFileReader(String filename) {
        this.filename = filename;
        this.fileExtension = ".json";
        this.records = new ArrayList<>();
    }

    public ArrayList<String[]> readFile() throws IOException {
        File file = new File(filename);
        String content = new String(Files.readAllBytes(file.toPath()));
        JSONArray jsonArray = new JSONArray(content);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            records.add(jsonObject);
            System.out.println(jsonObject);
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
