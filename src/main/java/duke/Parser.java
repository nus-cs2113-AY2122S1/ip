package duke;

import duke.task.*;

public class Parser {
    public static void parseInput(String lineInput) {
        String[] arrayInput = lineInput.split(" ");
        String commandInput = arrayInput[0];
        try {
            switch (commandInput) {
            case "list":
                TaskList.showTask();
                break;
            case "find":
                TaskList.findTask(arrayInput);
                break;
            case "done":
                TaskList.doneTask(Integer.parseInt(arrayInput[1]));
                break;
            case "delete":
                TaskList.deleteTask(Integer.parseInt(arrayInput[1]));
                break;
            case "deadline":
            case "event":
            case "todo":
                TaskList.recordTask(commandInput, lineInput);
                break;
            case "bye":
                UI.printByeMessage();
                break;
            default:
                System.out.println("OOPS!!! Sorry, but I do not understand:(");
                break;
            }
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("OOPS!!! Please enter a valid input:(");
            UI.printBreaker();
        }
    }
}