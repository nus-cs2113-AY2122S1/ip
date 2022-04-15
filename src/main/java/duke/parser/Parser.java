package duke.parser;

import duke.Ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

public class Parser {
    /**
     * Process the command of the user input
     * @param userInput string containing command and description of command
     * @return return the user input command without the description
     */
    public static String getCommand(String userInput) {
        String[] input = userInput.trim().toLowerCase().split(" ");
        return input[0];
    }

    /**
     * Process the input and add the task into the correct category
     * @param userInput string containing command and description of command
     * @param tasks ArrayList of tasks
     */
    public static void task(String userInput, ArrayList<Task> tasks) {
        String command = getCommand(userInput);
        try {
            switch (command) {
            case ("list"):
                TaskList.showList(tasks);
                break;
            case ("done"):
                TaskList.markAsDone(userInput, tasks);
                break;
            case ("todo"):
                TaskList.addToDo(userInput, tasks);
                break;
            case ("deadline"):
                TaskList.addDeadline(userInput, tasks);
                break;
            case ("event"):
                TaskList.addEvent(userInput, tasks);
                break;
            case ("delete"):
                TaskList.deleteTask(userInput, tasks);
                break;
            case ("find"):
                TaskList.findTask(userInput, tasks);
                break;
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            Ui.printHorizontalLine();
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            Ui.printHorizontalLine();
        }
    }

}