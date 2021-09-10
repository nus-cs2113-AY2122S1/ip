package utils;

import error.Error;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public static final String FILE_PATH = "data/savedTasks.txt";

    public static void parseFile() throws IOException {
        File f = new File(FILE_PATH);
        f.getParentFile().mkdir();
        if (f.createNewFile()) {
            return;
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
        }
    }

    public static void loadFileData() {
        try {
            parseFile();
        } catch (IOException e) {
            Error.displayFileCreateError();
        }
    }
}
