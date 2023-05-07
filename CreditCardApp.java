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
import javax.json.*;


public class CreditCardApp {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a filename as argument");
            System.exit(-1);
        }
        String filename = args[0];

        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        FormatFactory factory = null;
        switch(extension) {
            case "csv":
                factory = new CsvFactory();
            //case "json":
            //    factory = new JsonFactory();
            default:
                System.out.println("Unknown file type: " + filename);
        }
        IFileReader reader = factory.createReader(filename);
        IFileWriter writer = factory.createWriter(filename, reader);

        try {
            ArrayList<String[]> d = reader.readFile();
            //writer.writeFile(d);
        }  catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }



        //FileReaderFactory FRfactory = new FileReaderFactory();
        //IFileReader reader = FRfactory.createReader(filename);
        //ArrayList<String[]> d = reader.readFile();
        //String outputFile = "output_file" + reader.getFileExtension() ;
        //BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        //String header = "cardNumber,cardType";
        //writer.write(header + "\n");
        //for (int i = 1; i < d.size(); i++) {
        //    String[] row = d.get(i);
        //    String cardNum = row[0];
        //    if (checkNumber(cardNum) == "Valid") {
        //        CCFactory CardFactory = getFactory(cardNum);
        //        CreditCard cc = CardFactory.createCC(cardNum, app);
        //        if (cc.isValidCardNumber()) {
        //            String output1 = row[0] + "," + cc.getCardType() + "\n";
        //            writer.write(output1);
        //            System.out.println(output1);
        //        } else {
        //            String output3 = row[0] + "," + "Invalid: not a possible card number" + "\n";
        //            writer.write(output3);
        //            System.out.println(output3);
        //        }
        //    } else {
        //        String output2 = row[0] + "," + checkNumber(cardNum) + "\n";
        //        writer.write(output2);
        //        System.out.println(output2);
        //    }
        //}
        //writer.close();
    }

    public interface FormatFactory {
        public IFileReader createReader(String filename);
        public IFileWriter createWriter(String filename, IFileReader reader);
    }

    public interface IFileReader {
        public ArrayList<String[]> readFile() throws IOException;
        public String getFileExtension();
    }

    public interface IFileWriter {
        public void writeFile(ArrayList<String[]> data) throws IOException;
    }

    public static class CsvFactory implements FormatFactory {
        public IFileReader createReader(String filename) {
            return new CsvFileReader(filename);
        }
        public IFileWriter createWriter(String filename, IFileReader reader) {
            return new CsvFileWriter(filename, reader);
        }
    }

    public static  class JsonFactory implements FormatFactory {
        public IFileReader createReader(String filename) {
            return new JsonFileReader(filename);
        }
        //public IFileWriter createWriter(String filename) {
        //    return new JsonFileWriter(filename);
        //}
    }

    //public static class XmlFactory implements FormatFactory {
    //    public IFileReader createReader(String filename) {
    //        return new XmlFileReader(filename);
    //    }
    //    public IFileWriter createWriter(String filename) {
    //        return new XmlFileWriter(filename);
    //    }
    //}

    //public static class FileReaderFactory {
    //    public IFileReader createReader(String filename) {
    //        if (filename.endsWith(".csv")) {
    //            return new CsvFileReader(filename);
    //        }
    //        throw new IllegalArgumentException("Unknown file type: " + filename);
    //    }
    //}

    public static class CsvFileReader implements IFileReader {
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
            } catch (IOException e) {
                e.printStackTrace();
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

    public static class CsvFileWriter implements IFileWriter {
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
                        CCFactory CardFactory = getFactory(cardNum);
                        CreditCard cc = CardFactory.createCC(cardNum, app);
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
    }

    public static class JsonFileReader implements IFileReader {
        private final String filename;
        public final String fileExtension;
        public final ArrayList<String[]> records;
        public JsonFileReader(String filename) {
            this.filename = filename;
            this.fileExtension = ".json";
            this.records = new ArrayList<>();
        }
        public ArrayList<String[]> readFile() throws IOException, JSONException {
            ArrayList<String[]> records = new ArrayList<>();
           try (JsonReader reader = Json.createReader(new FileReader(filename))) {
               JsonObject jsonObject = reader.readObject();
               JsonArray jsonArray = jsonObject.getJsonArray("data");
               for (int i = 0; i < jsonArray.size(); i++) {
                   records.add(jsonArray.getString(i));
               }
           } catch (IOException e) {
               e.printStackTrace();
           } 
            System.out.println(records);
            return records;
        }
    }
    //public static class JsonFileWriter implements IFileWriter {
//
    //}



    public static String checkNumber(String cardNum) {
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

    public static CCFactory getFactory(String cardNum){
        if (cardNum.startsWith("5")) {
            return new MasterCardFactory();
        } else if (cardNum.startsWith("4")) {
            return new VisaCardFactory();
        } else if (cardNum.startsWith("3")) {
            return new AmexCardFactory();
        } else if (cardNum.startsWith("6011")) {
            return new DiscoverCardFactory();
        } else {
            return new UnknownCardFactory();
        }
    }

    public interface CCFactory {
        CreditCard createCC(String cardNum, CreditCardApp app);
    }

    public static class MasterCardFactory implements CCFactory {
        public CreditCard createCC(String cardNum, CreditCardApp app) {
            return new MasterCard(cardNum, app);
        }
    }

    public static class VisaCardFactory implements CCFactory {
        public CreditCard createCC(String cardNum, CreditCardApp app) {
            return new VisaCard(cardNum, app);
        }
    }

    public static class AmexCardFactory implements CCFactory {
        public CreditCard createCC(String cardNum, CreditCardApp app) {
            return new AmexCard(cardNum, app);
        }
    }

    public static class DiscoverCardFactory implements CCFactory {
        public CreditCard createCC(String cardNum, CreditCardApp app) {
            return new DiscoverCard(cardNum, app);
        }
    }

    public static class UnknownCardFactory implements CCFactory {
        public CreditCard createCC(String cardNum, CreditCardApp app) {
            return new UnknownCard(cardNum, app);
        }
    }


    abstract class CreditCard {
        private final String cardNum;
        private String cardType;

        public CreditCard(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getCardNum() {
            return cardNum;
        }

        public String getCardType() {
            return cardType;
        }

        protected void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public abstract boolean isValidCardNumber();
    }

    public static class MasterCard extends CreditCard {
        public MasterCard(String cardNum, CreditCardApp outer) {
            outer.super(cardNum);
            if(isValidCardNumber()) {
                setCardType("MasterCard");
            } else {
                setCardType("Invalid: not a possible card number");
            }
        }

        @Override
        public boolean isValidCardNumber() {
            boolean isValid = getCardNum().matches("^5[1-5]\\d{14}$");
            if (isValid) {
                setCardType("MasterCard");
            }
            return isValid;
        }
    }

    public static class VisaCard extends CreditCard {
        public VisaCard(String cardNum, CreditCardApp outer) {
            outer.super(cardNum);
            if(isValidCardNumber()) {
                setCardType("Visa");
            } else {
                setCardType("Invalid: not a possible card number");
            }
        }

        @Override
        public boolean isValidCardNumber() {
            boolean isValid = getCardNum().matches("^4\\d{12}(?:\\d{3})?$");
            if (isValid) {
                setCardType("Visa");
            } else {
                setCardType("Invalid: not a possible card number");
            }
            return isValid;
        }
    }

    public static class AmexCard extends CreditCard {
        public AmexCard(String cardNum, CreditCardApp outer) {
            outer.super(cardNum);
            if(isValidCardNumber()) {
                setCardType("AmericanExpress");
            } else {
                setCardType("Invalid: not a possible card number");
            }
        }

        @Override
        public boolean isValidCardNumber() {
            boolean isValid = getCardNum().matches("^3[47]\\d{13}$");
            if (isValid) {
                setCardType("AmericanExpress");
            } else {
                setCardType("Invalid: not a possible card number");
            }
            return isValid;
        }
    }

    public static class DiscoverCard extends CreditCard {
        public DiscoverCard(String cardNum, CreditCardApp outer) {
            outer.super(cardNum);
            if(isValidCardNumber()) {
                setCardType("Discover");
            } else {
                setCardType("Invalid: not a possible card number");
            }
        }

        @Override
        public boolean isValidCardNumber() {
            boolean isValid = getCardNum().matches("^6011\\d{12}$");
            if (isValid) {
                setCardType("Discover");
            } else {
                setCardType("Invalid: not a possible card number");
            }
            return isValid;
        }
    }

    public static class UnknownCard extends CreditCard {
        public UnknownCard(String cardNum, CreditCardApp outer) {
            outer.super(cardNum);
            if(isValidCardNumber()) {
                setCardType("not a possible card number");
            } else {
                setCardType("Invalid: not a possible card number");
            } 
        }

        @Override
        public boolean isValidCardNumber() {
            boolean isValid = getCardNum().matches("^6011\\d{12}$");
            if (isValid) {
                setCardType("not a possible card number");
            } else {
                setCardType("Invalid: not a possible card number");
            }
            return isValid;
        }
    }
}

