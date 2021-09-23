package duke.parser;


import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidTaskDescriptionException;
import duke.task.Task;

import java.util.ArrayList;

public class Parser {

    public static String getCommand(String userInput) {
        String[] input = userInput.trim().toLowerCase().split(" ");
        return input[0];
    }

    public static void parseCommand(String userInput, ArrayList<Task> tasks) {
        try {
            String command = getCommand(userInput);
            switch (command) {
                // abstract variables later depending on which class to put under
            case ("bye"):
                Ui.printFarewellMessage();
                break;
            case ("list"):
                TaskList.requestList(tasks);
                break;
            case ("done"):
            case ("undo"):
                TaskList.changeDoneStatus(userInput, tasks);
                break;
            case ("todo"):
                TaskList.addTodo(userInput, tasks);
                break;
            case ("deadline"):
            case ("event"):
                TaskList.addDeadlineOrEvent(userInput, tasks);
                break;
            case ("delete"):
                TaskList.deleteTask(userInput, tasks);
                break;
            default:
                Ui.printErrorMessage();
                break;
            }
        } catch (InvalidTaskDescriptionException e) {
            Ui.printHorizontalLine();
            System.out.println(e.getMessage());
            Ui.printHorizontalLine();
        }
    }

    public static String getTaskType(String userInput) {
        String[] description = userInput.trim().toLowerCase().split(" ");
        return description[0];
    }

    public static String getTodoDescription(String userInput) {
        int descriptionPosition = userInput.trim().indexOf(" ");
        return userInput.trim().substring(descriptionPosition);
    }

    public static int DeadlineOrEventDescriptionPosition(String userInput) {
        return userInput.trim().indexOf(" ");
    }

    public static int DeadlineOrEventTimePosition(String userInput) {
        return Math.max(userInput.trim().indexOf("/by"), userInput.trim().indexOf("/at"));
    }

    public static String getDeadlineOrEventDescription(String userInput) {
        int descriptionPosition = DeadlineOrEventDescriptionPosition(userInput);
        int timePosition = DeadlineOrEventTimePosition(userInput);
        return userInput.substring(descriptionPosition, timePosition);
    }

    public static String getDeadlineOrEventTime(String userInput) {
        int timePosition = DeadlineOrEventTimePosition(userInput);
        return userInput.substring(timePosition + 3);
    }

    public static int getTaskNumber(String userInput) {
        int taskNumberPosition = userInput.trim().indexOf(" ");
        return Integer.parseInt(userInput.trim().substring(taskNumberPosition + 1)) - 1;
    }

    public static boolean isValidTaskDescription(String userInput) {
        String[] description = userInput.trim().split(" ");
        return description.length > 1;
    }

    public static boolean isValidDeadlineOrEventDescription(String userInput, String description, String time) {
        if (!isValidDeadlineFormat(userInput) && !isValidEventFormat(userInput)) {
            return false;
        } else if (description.isEmpty() || time.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isValidDeadlineFormat(String userInput) {
        return userInput.contains("/by");
    }

    public static boolean isValidEventFormat(String userInput) {
        return userInput.contains("/at");
    }

}
