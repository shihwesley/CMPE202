package com.cmpe202;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlFileReader implements IFileReader {
    private final String filename;
    public final String fileExtension;
    public final ArrayList<CreditCard> creditcards;

    public XmlFileReader(String filename) {
        this.filename = filename;
        this.fileExtension = ".xml";
        this.creditcards= new ArrayList<CreditCard>();
    }

    public ArrayList<CreditCard> readFile() throws IOException {
        ArrayList<CreditCard> creditcards = new ArrayList<CreditCard>();
        File file = new File(filename);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("CARD");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String cardNumber = eElement.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();
                    if (cardNumber == null || cardNumber.isEmpty()) {
                        cardNumber = "";
                    }
                    String expirationDate = eElement.getElementsByTagName("EXPIRATION_DATE").item(0).getTextContent();
                    String nameOfCardHolder = eElement.getElementsByTagName("CARD_HOLDER_NAME").item(0).getTextContent();
                    CCFactory Factory = CCFactoryUtil.getFactory(cardNumber);
                    CreditCard cc = Factory.createCC(cardNumber);
                    cc.setCardExpirationDate(expirationDate);
                    cc.setNameOfCardHolder(nameOfCardHolder);
                    creditcards.add(cc);
                }
            }
            return creditcards;
        } catch (ParserConfigurationException | SAXException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return creditcards;
    }
}
