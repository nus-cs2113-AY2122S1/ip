package duke.parser;

import duke.tasklist.TaskManager;
import duke.tasklist.Task;
import duke.exceptions.DukeException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser extends TaskManager {

    public static final String STRING_BYE = "bye";
    public static final String STRING_HELP = "help";
    public static final String STRING_TO_DO = "to do ";
    public static final String STRING_DEADLINE = "deadline ";
    public static final String STRING_EVENT = "event ";
    public static final String STRING_LIST = "list";
    public static final String STRING_DELETE = "delete";
    public static final String STRING_DONE = "done ";
    public static final String STRING_FIND = "find ";

    /**
     * Parses user input into the different task list actions for execution.
     *
     * @param in object of scanner
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput full user input string
     */
    public static void parseCommand(Scanner in, ArrayList<Task> taskList, String userInput) {
        while (!userInput.startsWith(STRING_BYE)) {
            userInput = in.nextLine().toLowerCase();
            try {
                if (userInput.contains(STRING_HELP)) {
                    printHelpList();
                } else if (userInput.startsWith(STRING_TO_DO)) {
                    addTaskAsToDo(taskList, userInput, false);
                } else if (userInput.startsWith(STRING_DEADLINE)) {
                    addTaskAsDeadline(taskList, userInput, false);
                } else if (userInput.startsWith(STRING_EVENT)) {
                    addTaskAsEvent(taskList, userInput, false);
                } else if (userInput.startsWith(STRING_LIST)) {
                    printTaskList(taskList);
                } else if (userInput.startsWith(STRING_DELETE)) {
                    deleteTaskFromToDo(taskList, userInput);
                } else if (userInput.startsWith(STRING_DONE)) {
                    markTaskAsDone(taskList, userInput);
                } else if (userInput.startsWith(STRING_BYE)) {
                    break;
                } else if (userInput.startsWith(STRING_FIND)) {
                    find(taskList,userInput);
                } else {
                    printErrorForInvalidCommand(userInput);
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
