package duke;

import duke.task.*;

/**
 * Formats the inputs to pass to different methods.
 */
public class Parser {

    /**
     * Formats the input sent from the user and pass it to different methods accordingly.
     *
     * @param lineInput The line input from the user.
     */
    public static void parseInput(String lineInput)  {
        boolean printMessage = true;
        String[] arrayInput = lineInput.split(" ");
        String userCommand = arrayInput[0];
        try {
            switch (userCommand) {
            case "list":
                TaskList.showTask();
                break;
            case "done":
                int doneIndex = Integer.parseInt(arrayInput[1]);
                TaskList.doneTask(doneIndex, printMessage);
                break;
            case "find":
                TaskList.searchTask(arrayInput);
                break;
            case "event":
            case "todo":
            case "deadline":
                TaskList.recordTask(userCommand, lineInput, printMessage);
                break;
            case "delete":
                String deleteIndex = arrayInput[1];
                TaskList.deleteTask(deleteIndex);
                break;
            case "bye":
                break;
            default:
                System.out.println("OOPS!!! Sorry, but I do not understand:(");
                break;
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please enter a valid input:(");
        }
    }
}