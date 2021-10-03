package duke;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void getFromFile() {
        try {
            File file = new File("src/main/java/duke/data/task.txt");
            Scanner updateInput = new Scanner(file);
            int taskNumber = 1;
            while (updateInput.hasNext()) {
                String[] lineInput = updateInput.nextLine().split("\\|");
                switch (lineInput[0]) {
                case "D ":
                    duke.TaskList.recordTask("deadline", "deadline" + lineInput[2]);
                    break;
                case "E ":
                    duke.TaskList.recordTask("deadline", "event" + lineInput[2]);
                    break;
                case "T ":
                    duke.TaskList.recordTask("deadline", "todo" + lineInput[2]);
                    break;
                }
                if (lineInput[1].equals(" 1 ")) {
                    duke.TaskList.doneTask(taskNumber);
                }
                taskNumber++;
            }
        } catch (IOException | DukeException e) {
            System.out.println("There is no saved file:( but I will create a new one for you:)");
            duke.UI.printBreaker();
        }
    }

    public static void saveToFile(ArrayList<Task> listInput, int taskNumber) {
        try {
            FileWriter file = new FileWriter("src/main/java/duke/data/task.txt");
            for (int i = 0; i < taskNumber; i++) {
                String numberStatus = ((listInput.get(i)).getStatus().equals("[X] ") ? "1" : "0");
                String taskType = (listInput.get(i)).getType();
                String taskName = (listInput.get(i)).getName();
                file.write(taskType + " | " + numberStatus + " | " + taskName + "\n");
            }
            file.close();
        }catch (IOException e) {
            System.out.println("There is no valid file:(");
        }
    }
}