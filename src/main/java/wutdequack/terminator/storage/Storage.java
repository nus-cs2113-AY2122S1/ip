package wutdequack.terminator.storage;

import static wutdequack.terminator.common.MagicValues.DEADLINE_TYPE;
import static wutdequack.terminator.common.MagicValues.EVENT_TYPE;
import static wutdequack.terminator.common.MagicValues.FILE_LOCATION;
import static wutdequack.terminator.common.MagicValues.FROM_FILE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import wutdequack.terminator.Terminator;
import wutdequack.terminator.objects.task.Task;
import wutdequack.terminator.tasklist.TaskList;
import wutdequack.terminator.ui.TextUi;

public class Storage {

    private TextUi ui;
    private TaskList taskList;

    public Storage() {
        this.ui = new TextUi();
        this.taskList = new TaskList();
    }

    /**
     * Reads Strings from file, add them to arraylist and return arraylist.
     */
    public ArrayList<String> readFromFile() {
        ArrayList<String> returnArrayList = new ArrayList<String>();
        try {
            File fileObject = new File(FILE_LOCATION);
            Scanner scanObject = new Scanner(fileObject);

            // Iterate through the lines and add it into the arrayList.
            while (scanObject.hasNext()) {
                returnArrayList.add(scanObject.nextLine());
            }
        } catch (FileNotFoundException e) {
            handleFileNotFound();
        }
        return returnArrayList;
    }

    /**
     * Stores given string into the file path.
     * @param stringToBeSaved A string to be be stored into the file.
     */
    private void writeToFile(String stringToBeSaved) {
        try {
            FileWriter fw = new FileWriter(FILE_LOCATION);
            fw.write(stringToBeSaved);
            fw.close();
        } catch (IOException e) {
            handleFileNotFound();
        }
    }

    /**
     * Handles the scenario if a file is not found.
     */
    private void handleFileNotFound() {
        ui.printFileDontExistMessage();
        try {
            File fileObject = new File(FILE_LOCATION);
            fileObject.getParentFile().mkdirs();
            if (fileObject.createNewFile()) {
                ui.printFileCreationMessage();
            }
        } catch (IOException e) {
            ui.printFilePermissionsErrorMessage();
            new Terminator().handleByeSequence();
        }
    }

    /**
     * Handler function for updating Tasks to File.
     */
    public void updateTasksToFile() {
        String generatedString = generateStringToStore();
        writeToFile(generatedString);
    }

    /**
     * Handler function for updating Tasks from File.
     */
    public void loadTasksFromFile() {
        ArrayList<String> contentFromFile =  readFromFile();
        facilitateTaskObjectCreation(contentFromFile);
    }

    /**
     * Facilitates the task object creation given the strings from the file.
     * @param fileFormattedTaskStrings An ArrayList of Strings read from a file
     */
    private void facilitateTaskObjectCreation(ArrayList<String> fileFormattedTaskStrings) {
        for (String fileFormatString: fileFormattedTaskStrings) {
            // Get the first char from the string
            String firstChar = fileFormatString.substring(0,1);
            switch (firstChar.toUpperCase()) {
            case EVENT_TYPE:
                taskList.createEventTask(fileFormatString, FROM_FILE);
                break;
            case DEADLINE_TYPE:
                taskList.createDeadlineTask(fileFormatString, FROM_FILE);
                break;
            default:
                taskList.createToDoTask(fileFormatString, FROM_FILE);
            }
        }
    }

    /**
     * Generates string version of all the tasks to be stored.
     * @return A String of all the tasks to be stored in a text file.
     */
    private String generateStringToStore() {
        StringJoiner taskJoiner = new StringJoiner(System.lineSeparator());
        for (Task t: taskList.getTasksList()) {
            taskJoiner.add(t.toFileStringFormat());
        }
        return taskJoiner.toString();
    }

}
