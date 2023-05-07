package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CsvFileWriter implements IFileWriter {
    private final String filename;
    private final IFileReader reader;

    public CsvFileWriter(String filename, IFileReader reader) {
        this.filename = filename;
        this.reader = reader;
    }
    public void writeFile(ArrayList<String[]> data) {
        CreditCardApp app = new CreditCardApp();
        String outputFile = "output_file" + reader.getFileExtension() ;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String header = "cardNumber,cardType";
            writer.write(header + "\n");
            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                String cardNum = row[0];
                if (checkNumber(cardNum) == "Valid") {
                    CCFactory Factory = CCFactoryUtil.getFactory(cardNum);
                    CreditCard cc = Factory.createCC(cardNum, app);
                    if (cc.isValidCardNumber()) {
                        String output1 = row[0] + "," + cc.getCardType() + "\n";
                        writer.write(output1);
                        System.out.println(output1);
                    } else {
                        String output3 = row[0] + "," + "Invalid: not a possible card number" + "\n";
                        writer.write(output3);
                        System.out.println(output3);
                    }
                } else {
                    String output2 = row[0] + "," + checkNumber(cardNum) + "\n";
                    writer.write(output2);
                    System.out.println(output2);
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    public String checkNumber(String cardNum) {
        String regex = "^(?!.{20})(?:\\d+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNum);

        if (cardNum.isEmpty()) 
        {
            return "Invalid: empty/null card number";
        } else if (cardNum.length() > 19) {
            return "Invalid: more than 19 digits";
        } else if (!matcher.matches()) {
            return "Invalid: non numeric characters";
        } else {
            return "Valid";
        }
    }
}