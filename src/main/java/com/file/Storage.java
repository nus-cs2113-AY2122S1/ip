package com.file;

import com.task.Task;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {
    private final static int FILE_MAX_LINES = 100;
    String fileName;
    String filePath;
    public Storage(String fileName){
        this.fileName = fileName;
        this.filePath = "data/"+ this.fileName;
    }

    public void checkPath() throws IOException {
        File directory = new File("data");
        directory.mkdir();
        File f = new File(filePath);
        f.createNewFile();
    }

    public ArrayList<String> readFile() throws IOException {
        checkPath();
        ArrayList<String> lines = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        return lines;
    }


    public void rewriteFile(ArrayList<Task> lines) throws IOException {
        checkPath();
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        for(Task line : lines) {
            fw.append(line.toString()).append("\n");
        }
        fw.close();
    }

}
