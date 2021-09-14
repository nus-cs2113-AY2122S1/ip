package duke;

import duke.actions.Task;
import duke.exceptions.DukeException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke extends TaskManager {

    public static final String FILE_PATH = "duke.txt";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String userInput = "";
        printIntro();
        DataManager.printPreviousFileContents(FILE_PATH, taskList);
        while (!userInput.startsWith("bye")) {
            userInput = in.nextLine().toLowerCase();
            try {
                if (userInput.contains("help")) {
                    printHelpList();
                } else if (userInput.startsWith("to do ")) {
                    addTaskAsToDo(taskList, userInput, false);
                } else if (userInput.startsWith("deadline ")) {
                    addTaskAsDeadline(taskList, userInput, false);
                } else if (userInput.startsWith("event ")) {
                    addTaskAsEvent(taskList, userInput, false);
                } else if (userInput.startsWith("list")) {
                    printTaskList(taskList);
                } else if (userInput.startsWith("delete")) {
                    deleteTaskFromToDo(taskList, userInput);
                } else if (userInput.startsWith("done ")) {
                    markTaskAsDone(taskList, userInput);
                } else if (userInput.startsWith("bye")) {
                    break;
                } else {
                    printErrorForInvalidCommand(userInput);
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        DataManager.storeCurrentList(FILE_PATH, taskList);
        printOutro();
    }
}
