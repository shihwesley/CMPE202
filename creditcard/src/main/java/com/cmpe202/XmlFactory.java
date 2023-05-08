package com.cmpe202;

public class XmlFactory implements FormatFactory {
    public IFileReader createReader(String filename) {
        return new XmlFileReader(filename);
    }
    public IFileWriter createWriter(String extension) {
        return new XmlFileWriter(extension);
    }
}