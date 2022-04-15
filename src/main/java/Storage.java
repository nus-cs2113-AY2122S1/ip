import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    /**
     * Function appends user input text to a file, except for the command "bye".
     *
     * @param filePath     path to the file being written
     * @param textToAppend user input
     * @throws IOException IOException
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        if (!textToAppend.equals("bye")) {
            fw.write(textToAppend + "\n");
            fw.close();
        }
    }

    public static void saveTask(String query) {
        String FILEPATH = "./list.txt";
        try {
            appendToFile(FILEPATH, query);
        } catch (IOException e) {
            System.out.println("Something went wrong... ------>" + e.getMessage());
        }
    }

    /**
     * Function loads tasks from list.txt file. If file is not found, it will b created after the user's first input.
     */
    public static void loadTask() {
        String query;
        String FILEPATH = "./list.txt";
        try {
            File file = new File(FILEPATH);
            Scanner listInput = new Scanner(file);
            while (listInput.hasNext()) {
                query = listInput.nextLine();
                TaskList.addTask(query);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong... ------>" + e.getMessage());
            System.out.println("Your next input will now create a file called list.txt");
        }
    }
}
