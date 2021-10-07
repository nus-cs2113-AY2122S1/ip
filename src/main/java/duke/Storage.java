package duke;

import java.util.Scanner;

import java.io.IOException;

import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;

import duke.task.*;

/**
 * Reads from a text file and writes to the file to store tasks.
 */
public class Storage {

    /**
     * Gets the task list from the file.
     */
    public static void getFromFile() {
        try {
            File file = new File("src/main/java/duke/data/task.txt");
            file.createNewFile();
            Scanner updateInput = new Scanner(file);
            boolean printMessage = false;
            int taskNumber = 1;
            while (updateInput.hasNext()) {
                String[] arrayInput = updateInput.nextLine().split("\\|");
                switch (arrayInput[0]) {
                case "D ":
                    TaskList.recordTask("deadline", "deadline" + arrayInput[2], printMessage);
                    break;
                case "E ":
                    TaskList.recordTask("event", "event" + arrayInput[2], printMessage);
                    break;
                case "T ":
                    TaskList.recordTask("todo", "todo" + arrayInput[2], printMessage);
                    break;
                }
                if (arrayInput[1].equals(" 1 ")) {
                    TaskList.doneTask(taskNumber, printMessage);
                }
                taskNumber++;
            }
        } catch (IOException e) {
            System.out.println("There is no saved file:( but I will create a new one for you:)");
        }
    }

    /**
     * Saves the task list to the file.
     *
     * @param listInput The task list to be saved.
     * @param taskNumber The integer total number of tasks in the list.
     */
    public static void saveToFile(ArrayList<Task> listInput, int taskNumber) {
        try {
            FileWriter file = new FileWriter("src/main/java/duke/data/task.txt");
            for (int i = 0; i < taskNumber; i++) {
                String numberStatus = ((listInput.get(i).getTaskStatus().equals("[X] "))? "1" : "0");
                String stringToSave = listInput.get(i).getTaskType() + " | " + numberStatus +  " | " + listInput.get(i).getTaskDescription() + "\n";
                file.write(stringToSave);
            }
            file.close();
        } catch (IOException e) {
            System.out.println("There is no valid file:(");
        }
    }
}