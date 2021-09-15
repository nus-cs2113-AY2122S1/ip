package com.file;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final static int FILE_MAX_LINES = 100;
    String fileName;
    String filePath;
    public FileManager (String fileName) throws IOException {
        this.fileName = fileName;
        this.filePath = "data/"+ this.fileName;
        File directory = new File("data");
        directory.mkdir();
        File f = new File(filePath);
        f.createNewFile();
    }

    public ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        return lines;
    }

    public void rewriteFile(ArrayList<String> lines) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        for(String line : lines) {
            fw.append(line).append("\n");
        }
        fw.close();
        System.out.println("file written");
    }

    public void addToFile(String line) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.append(line);
        fw.close();
        System.out.println("file written");
    }

}
