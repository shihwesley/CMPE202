package src;

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

import src.IFileReader;
import src.IFileWriter;

public interface FormatFactory {
    public IFileReader createReader(String filename);
    public IFileWriter createWriter(String filename, IFileReader reader);
}

//public static class XmlFactory implements FormatFactory {
//    public IFileReader createReader(String filename) {
//        return new XmlFileReader(filename);
//    }
//    public IFileWriter createWriter(String filename) {
//        return new XmlFileWriter(filename);
//    }
//}
