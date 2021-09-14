import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileManager {
    private static final String home = System.getProperty("user.home");
    private static final Path dukeDirPath = Paths.get(home, "Duke");
    private static final File dukeDir = new File(dukeDirPath.toString());
    private static final Path dataPath = Paths.get(home, "Duke/data.txt");
    private static final File data = new File(dataPath.toString());

    public static void initializeFile() throws IOException {
        //dukeDir directory did not exist
        //create Duke directory
        if (dukeDir.mkdir()) {
            data.createNewFile();

        } else { //dukeDir directory exists
            //create Duke/data.txt if does not exist
            // run the if statement if Duke/data.txt exists already
            if (!data.createNewFile()) {
                try {
                    readFile();
                } catch (FileNotFoundException e) {
                    System.out.println(home + "/Duke/data.txt not found!!!");
                }
                Duke.taskManager.listTasks();
            }
        }
    }

    private static void readFile() throws FileNotFoundException {
        Scanner s = new Scanner(data); // create a Scanner using the File as the source
        while (s.hasNext()) {
            parseData(s.nextLine());
        }
        // TODO exception for failing to read a particular line?????/
    }


    private static void parseData(String line) {
        String[] taskDetails = line.split(" \\| "); //TODO if userInput includes '|', might need to reject..?
        // need to escape pipe operator as it is a metacharacter in regex

        String taskLetter = taskDetails[0];

        String isDoneString = taskDetails[1];
        int isDoneInt = Integer.parseInt(isDoneString);
        boolean isDone = (isDoneInt == 1); //TODO how to account for errors in textfile... beside 1, 0
        //TODO EXCEPTIONS: skip lines with error, and notify user that these lines have error

        String description = taskDetails[2];
        String date;

        switch (taskLetter) {
        case "T":
            Duke.taskManager.addTodo(description, isDone);
            break;

        case "D":
            date = taskDetails[3];
            String[] deadlineDetails = { description, date };
            
            Duke.taskManager.addDeadline(deadlineDetails, isDone);
            break;

        case "E":
            date = taskDetails[3];
            String[] eventDetails = { description, date };
            Duke.taskManager.addEvent(eventDetails, isDone);
            break;
        }
    }

    //rewrite entire data.txt file with latest version of tasks list
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(data);
        fw.write(Duke.taskManager.getTasksDataStorageString());
        fw.close();
    }

}
