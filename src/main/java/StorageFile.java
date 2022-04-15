import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The StorageFile program handles the creation of data file, reading existing tasks in it and the writing of new tasks
 * to the file.
 */
public class StorageFile {
    protected String fileName;
    protected String directoryName;
    protected boolean hasData;
    protected int count = 0;
    protected String date = "";
    protected ArrayList<String> Data = new ArrayList<>();
    protected ArrayList<Task> Task = new ArrayList<>();



    /**
     * Creates a new file named "duke.txt" in the given file path and prints a creation message. In the case the file
     * already exists, prints a message stating its existence. Also prints an error message in case any error occurs.
     */
    public void create() {
        System.out.println("Enter the path to create a directory:  [e.g. C:\\DUKE\\]");
        Scanner sc = new Scanner(System.in);
        String directoryPath = sc.next();
        File directory = new File(directoryPath);
        boolean createdDir = directory.mkdir();
        if (createdDir) {
            System.out.println("Directory created successfully: " + directoryPath);
        } else {
            System.out.println("Directory already exists: " + directoryPath);
        }


        String name = "duke.txt";
        fileName = directoryPath + "\\" + name;

        try {
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName() + "\n");
            }
            else {
                System.out.println("File already exists: " + myFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.\n");
            e.printStackTrace();
        }
    }

    /**
     * Scans every line in duke.txt file as a String and stores it in a String Array.
     * @throws FileNotFoundException when file is not found at given file path
     */
    public void read() throws FileNotFoundException {
        File f = new File(fileName); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            hasData = true;
            String word = s.nextLine();
            Data.add(word);
        }
    }

    /**
     * Saves the tasks by writing to the text file.
     * @param i index of Task array to differentiate between writing over and appending in the text file
     * @param text Task to write in the text file
     */
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

    /**
     * Writes over existing text in text file
     * @param filePath where the text file is kept
     * @param textToAdd text to write to file
     * @throws IOException when method is unable to write to file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends text to text file
     * @param filePath where the text file is kept
     * @param textToAppend text to append to file
     * @throws IOException when method is unable to append to file
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
