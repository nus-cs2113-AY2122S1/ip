package duke;

import duke.command.TaskParser;
import duke.task.Todo;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();
    static final String FILENAME = "tasklist";

    private static void saveTaskListToFile() {
        try {
            FileOutputStream fileos = new FileOutputStream(FILENAME);
            ObjectOutputStream objos = new ObjectOutputStream(fileos);
            objos.writeObject(taskList);
        } catch (IOException e) {
            System.out.println("Error saving to file.");
            System.out.println(e.getMessage());
        }
    }

    private static void readTaskListFromFile() {
        try {
            FileInputStream fileis = new FileInputStream(FILENAME);
            ObjectInputStream objis = new ObjectInputStream(fileis);
            taskList = (ArrayList<Task>)objis.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file.");
            System.out.println(e.getMessage());
        }
    }

    private static void printWithIndex(List<?> list) {
        for (int i = 1; i <= list.size(); i += 1) {
            System.out.println(i + ": " + list.get(i - 1));
        }
    }

    private static boolean handleOneInputLine(String line) {
        String[] splitted = line.split("\\s+");
        switch (splitted[0]) {
        case "bye":
            return true;
        case "list":
            printWithIndex(taskList);
            break;
        case "done": {
            Task target = taskList.get(Integer.parseInt(splitted[1]) - 1);
            target.setDoneStatus(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target);
            break;
        }
        case "delete": {
            Task target = taskList.remove(Integer.parseInt(splitted[1]) - 1);
            saveTaskListToFile();
            System.out.println("Noted. I've removed this task:");
            System.out.println(target);
            break;
        }
        case "find": {
            String needle = splitted[1];
            List<Task> filteredTasks = taskList.stream().filter(t -> t.getTitle().contains(needle)).collect(Collectors.toList());
            printWithIndex(filteredTasks);
            break;
        }
        default:
            Task newTask = null;
            try {
                newTask = TaskParser.parseTask(splitted);
            } catch (ArrayIndexOutOfBoundsException |
                     IllegalArgumentException e) {
                throw new ArrayIndexOutOfBoundsException(
                    "OOPS! Some required arguments are missing.");
            }
            taskList.add(newTask);
            saveTaskListToFile();
            System.out.println("added: " + newTask);
        }
        return false;
    }

    private static void initialise() {
        readTaskListFromFile();
        String greeting = "Hello! I'm Duke";
        String assist = "What can I do for you?";
        System.out.println(greeting);
        System.out.println(assist);
    }

    private static void finalise() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        initialise();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                if (handleOneInputLine(sc.nextLine())) {
                    finalise();
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        finalise();
    }
}
