package duke.parser;

import duke.tasklist.TaskManager;
import duke.tasklist.Task;
import duke.exceptions.DukeException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser extends TaskManager {

    public static void parseCommand(Scanner in, ArrayList<Task> taskList, String userInput) {
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
                } else if (userInput.startsWith("find ")) {
                    searchForTask(taskList,userInput);
                } else {
                    printErrorForInvalidCommand(userInput);
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
