package herrekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileWriting {

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void deleteFile(String filePath) throws IOException {
        File f = new File(filePath);
        f.delete();
    }

    public static List<String> convertSavedFileToCurrentTimetable(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        List<String> taskInStringList = new ArrayList<>();
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        while (sc.hasNext()) {
            taskInStringList.add(sc.nextLine());
        }
        return taskInStringList;
    }

    public static void main(String[] args) {
        String file = "text-ui-test/test.txt";
        try {
            writeToFile(file, "first line" + System.lineSeparator() + "second line");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}