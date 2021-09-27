package duke.storage;

import duke.Duke;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static void appendFileContentsToArrayList() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] commandInput = s.nextLine().split(" \\| ",4);
            String commandWord = commandInput[0];
            String isCompleteString = commandInput[1];
            String taskDescription = commandInput[2];
            String additionalDescription;
            if (commandWord.equals("T")) {
                additionalDescription = "";
            } else {
                additionalDescription = commandInput[3];
            }
            switch (commandWord) {
            case ("T"):
                Duke.tasks.add(new Todo(taskDescription));
                Duke.setTaskAsDone(isCompleteString);
                break;
            case("D"):
                Duke.tasks.add(new Deadline(taskDescription, additionalDescription));
                Duke.setTaskAsDone(isCompleteString);
                break;
            case("E"):
                Duke.tasks.add(new Event(taskDescription, additionalDescription));
                Duke.setTaskAsDone(isCompleteString);
                break;
            }
        }
    }

    public static void writeToFile(String taskInstance, String rawText, String additionalText, boolean isDone) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true);
        String additionalTextWithBorders = (additionalText.equals("") ? "" : " | " + additionalText);
        String taskAsText = taskInstance + " | " + Duke.isDoneString(isDone) + " | " + rawText + additionalTextWithBorders + System.lineSeparator();
        fw.write(taskAsText);
        fw.close();
    }

    public static void appendEventToFile(String description, String at) {
        try {
            writeToFile("E",description,at,false);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    public static void appendDeadlineToFile(String description, String by) {
        try {
            writeToFile("D",description,by,false);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    public static void appendTodoToFile(String todoInput) {
        try {
            writeToFile("T", todoInput,"", false);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    public static void clearFile() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("IO Error!");
        }
    }
}
