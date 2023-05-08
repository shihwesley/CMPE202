package com.cmpe202;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlFileWriter implements IFileWriter {
    private final String extension;
    private final String filename;

    public XmlFileWriter(String extension) {
        this.extension = extension;
        this.filename = "output_file." + extension;
    }

    public void writeFile(ArrayList<CreditCard> creditCards) throws IOException {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(new File(filename)));

            String indent = "  ";
            String indents = "      ";
            String newline = System.getProperty("line.separator");

            writer.writeStartElement("CARDS");
            writer.writeCharacters("\n");

            for (CreditCard creditCard : creditCards) {
                writer.writeCharacters(indent);
                writer.writeStartElement("CARD");
                writer.writeCharacters(newline);

                writer.writeCharacters(indents);
                writer.writeStartElement("CARD_NUMBER");
                if (creditCard.getCardNum() != "" && creditCard.getCardNum() != null && !creditCard.getCardNum().isEmpty()) {
                    writer.writeCharacters(creditCard.getCardNum());
                } else {
                    writer.writeCharacters("");
                }
                writer.writeEndElement();
                writer.writeCharacters(newline);

                writer.writeCharacters(indents);
                writer.writeStartElement("CARD_TYPE");
                writer.writeCharacters(creditCard.getCardType());
                writer.writeEndElement();
                writer.writeCharacters(newline);

                writer.writeCharacters(indent);
                writer.writeEndElement();
                writer.writeCharacters(newline);
            }
            writer.writeEndElement();
            writer.writeCharacters(newline);

            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
    
}
