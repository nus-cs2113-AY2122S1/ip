import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class FileManager {
    private static final String home = System.getProperty("user.home");
    private static final Path dukeDirPath = Paths.get(home, "Duke");
    private static final Path dataPath = Paths.get(home, "Duke/data.txt");

    private static final File dukeDir = new File(dukeDirPath.toString());
    private static final File data = new File(dataPath.toString());

    public static void initializeFile() throws IOException {

        if (dukeDir.exists()) {
            if (data.exists()) {
                //TODO read from Duke/data
                try {
                    readFile();

                } catch (FileNotFoundException e) {
                    System.out.println("$HOME/Duke/data.txt not found!!!");

                }
                Duke.taskManager.listTasks();


            } else {
                data.createNewFile();

            }
        } else {
            dukeDir.mkdir();
            data.createNewFile();
        }
        
    }

    private static void readFile() throws FileNotFoundException {
        Scanner s = new Scanner(data); // create a Scanner using the File as the source
        while (s.hasNext()) {
            // System.out.println(s.nextLine());
            parseData(s.nextLine());
        }

        // TODO exception for failing to read a particular line?????/

    }

    private static void parseData(String line) {
        String[] taskDetails = line.split(" \\| "); //TODO if userInput includes '|', might need to reject..?
        // need to escape pipe operator as it is a metacharacter in regex

        String taskLetter = taskDetails[0];

        String isDoneString = taskDetails[1];
        Integer isDoneInteger = Integer.parseInt(isDoneString);
        Boolean isDone = (isDoneInteger.intValue() == 1) ? true : false; //TODO how to account for errors in textfile... beside 1, 0

        String description = taskDetails[2];
        String date;

        switch (taskLetter) {
            case "T":
                Duke.taskManager.addTodoFromFile(description, isDone);
                break;

            case "D":
                date = taskDetails[3];
                Duke.taskManager.addDeadlineFromFile(description, date, isDone);
                break;

            case "E":
                date = taskDetails[3];
                Duke.taskManager.addEventFromFile(description, date, isDone);
                break;
        }
        
        

    }




}
