package com.cmpe202;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Before
    public void testWriteCsv() throws IOException {
        ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
        creditCards.add(new MasterCard("5567894523129089"));
        creditCards.add(new VisaCard("4111111111111111"));
        creditCards.get(0).setCardExpirationDate("10/20");
        creditCards.get(0).setNameOfCardHolder("John Doe");
        creditCards.get(1).setCardExpirationDate("11/21");
        creditCards.get(1).setNameOfCardHolder("Jane Doe");
        CsvFileWriter csvFileWriter = new CsvFileWriter("csv");
        JsonFileWriter jsonFileWriter = new JsonFileWriter("json");
        XmlFileWriter xmlFileWriter = new XmlFileWriter("xml");
        csvFileWriter.writeFile(creditCards);
        jsonFileWriter.writeFile(creditCards);
        xmlFileWriter.writeFile(creditCards);
    }
    //@Test
    //public void testReadCsv() throws IOException {
    //    String Csvfilename = "output_file.csv";
    //    CsvFileReader csvFileReader = new CsvFileReader(Csvfilename);
    //    ArrayList<CreditCard> actual = csvFileReader.readFile();
    //    ArrayList<CreditCard> expected = new ArrayList<CreditCard>();
    //    expected.add(new MasterCard("5567894523129089"));
    //    expected.add(new VisaCard("4111111111111111"));
    //    assertEquals(expected.size()+1, actual.size());
    //    for (int i = 0; i < expected.size(); i++) {
    //        assertEquals(expected.get(i).getCardNum(), actual.get(i+1).getCardNum());
    //    }
    //}

    //@Test
    //public void testReadJson() throws IOException {
    //    String Jsonfilename = "output_file-2.json";
    //    JsonFileReader JsonFileReader = new JsonFileReader(Jsonfilename);
    //    ArrayList<CreditCard> actual = JsonFileReader.readFile();
    //    ArrayList<CreditCard> expected = new ArrayList<CreditCard>();
    //    expected.add(new MasterCard("5567894523129089"));
    //    expected.add(new VisaCard("4111111111111111"));
    //    assertEquals(expected.size(), actual.size());
    //    for (int i = 0; i < expected.size(); i++) {
    //        assertEquals(expected.get(i).getCardNum(), actual.get(i).getCardNum());
    //    }
    //}
//
    //@Test
    //public void testReadXml() throws IOException {
    //    String Xmlfilename = "output_file.xml";
    //    XmlFileReader XmlFileReader = new XmlFileReader(Xmlfilename);
    //    ArrayList<CreditCard> actual = XmlFileReader.readFile();
    //    ArrayList<CreditCard> expected = new ArrayList<CreditCard>();
    //    expected.add(new MasterCard("5567894523129089"));
    //    expected.add(new VisaCard("4111111111111111"));
    //    assertEquals(expected.size(), actual.size());
    //    for (int i = 0; i < expected.size(); i++) {
    //        assertEquals(expected.get(i).getCardNum(), actual.get(i).getCardNum());
    //    }
    //}
}
