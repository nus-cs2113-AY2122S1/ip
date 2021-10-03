package duke;

import duke.task.*;

public class Parser {
    public static void parseInput(String lineInput) {
        String[] arrayInput = lineInput.split(" ");
        String commandInput = arrayInput[0];
        try {
            switch (commandInput) {
            case "list":
                duke.TaskList.showTask();
                break;
            case "find":
                duke.TaskList.findTask(arrayInput);
                break;
            case "done":
                duke.TaskList.doneTask(Integer.parseInt(arrayInput[1]));
                break;
            case "delete":
                duke.TaskList.deleteTask(Integer.parseInt(arrayInput[1]));
                break;
            case "deadline":
            case "event":
            case "todo":
                duke.TaskList.recordTask(commandInput, lineInput);
                break;
            case "bye":
                duke.UI.printByeMessage();
                break;
            default:
                System.out.println("OOPS!!! Sorry, but I do not understand:(");
                break;
            }
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("OOPS!!! Please enter a valid input:(");
            duke.UI.printBreaker();
        }
    }
}