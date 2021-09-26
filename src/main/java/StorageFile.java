import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageFile {
    protected String fileName;
    protected boolean hasData;
    protected int count = 0;
    protected String date = "";
    protected ArrayList<String> Data = new ArrayList<>();
    protected ArrayList<Task> Task = new ArrayList<>();

    public StorageFile(String path) {
        this.fileName = path;
    }

    public void create() {
        try {
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName() + "\n");
            } else {
                System.out.println("File already exists.\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.\n");
            e.printStackTrace();
        }
    }

    public void read() throws FileNotFoundException {
        File f = new File(fileName); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            hasData = true;
            String word = s.nextLine();
            Data.add(word);
        }
    }

    public void write(int i, String text) {
        if (i==0) {
            try {
                writeToFile(fileName, text + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        else {
            try {
                appendToFile(fileName, text + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
