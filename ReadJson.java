import java.io.File;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {

    public static void main(String[] args) throws IOException {
        // Create a JSON parser
        JSONParser parser = new JSONParser();

        // Read the JSON file
        File jsonFile = new File("data.json");
        JSONObject jsonObject = (JSONObject) parser.parse(jsonFile);

        // Print the JSON object
        System.out.println(jsonObject);

        // Get the "cardNumber" property from the JSON object
        String cardNumber = (String) jsonObject.get("cardNumber");

        // Print the "name" property
        System.out.println("cardNumber: " + cardNumber);

        // Get the "expirationDate" property from the JSON object
        int expirationDate = (String) jsonObject.get("expirationDate");

        // Print the "expirationDate" property
        System.out.println("expirationDate: " + expirationDate);

        // Get the "Name" property from the JSON object
        String cardHolderName = (String) jsonObject.get("cardHolderName");

        // Print the "name" property
        System.out.println("cardHolderName: " + cardHolderName);
    }
}