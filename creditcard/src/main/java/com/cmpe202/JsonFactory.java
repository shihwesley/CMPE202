package com.cmpe202;

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


public class JsonFactory implements FormatFactory {
    public IFileReader createReader(String filename) {
        return new JsonFileReader(filename);
    }
    public IFileWriter createWriter(String extension) {
        return new JsonFileWriter(extension);
    }
}