import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class Storage {
    public static void loadData(File f, Scanner readFile) {
        while (readFile.hasNext()) {
            String information = readFile.nextLine();
            parseInformation(information);
        }
    }

    private static void parseInformation(String information) {
        String[] result = information.split(" \\| ");
        checkTaskType(result);
    }

    private static void checkTaskType(String[] result) {
        Task newTask;
        switch (result[0]) {
        case "T":
            newTask = new Todo(result[2], strToBoolean(result[1]));
            Greet.reloadTask(newTask);
            break;
        case "D":
            newTask = new Deadline(result[2], strToBoolean(result[1]), result[3]);
            Greet.reloadTask(newTask);
            break;
        case "E":
            newTask = new Event(result[2], strToBoolean(result[1]), result[3]);
            Greet.reloadTask(newTask);
            break;
        }
    }


    private static boolean strToBoolean(String s) {
        return !s.equals("0");
    }


    public static void storeData(FileWriter fileWrite) throws IOException {
        ArrayList<Task> list = Greet.getList();
        for(Task task: list){
            fileWrite.write(parseTask(task) + lineSeparator());
        }
        fileWrite.close();
    }

    private static String parseTask(Task task) {
        String newString;
            newString = task.getTaskType() + " | " + booleanInt(task.isDone) +
                    " | " + task.getDescription();
            if (task instanceof Event) {
                Event event = (Event)task;
                newString = newString + " | " + event.getDate();
            } else if (task instanceof Deadline){
                Deadline deadline = (Deadline)task;
                newString = newString + " | " + deadline.getDate();
            }
            return newString;
    }

    private static int booleanInt(boolean isDone) {
        return isDone ? 1 : 0;
    }
}
