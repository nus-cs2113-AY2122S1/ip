package duke.ui;

import duke.util.Alarm;

import static duke.Duke.taskCount;

public class ErrorReport {
    public static void alarm(Alarm typeOfAlarm) {
        switch(typeOfAlarm) {
        case INVALID_COMMAND:
            System.out.println("Invalid charm. What did Professor Flitwick told you?");
            //System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        case BLANK_DESCRIPTION:
            System.out.println("Hermoine says the description of a task cannot be empty.");
            break;
        case EMPTY_DONE:
            System.out.println("Give me a number.");
            break;
        case EMPTY_TODO:
            System.out.println("☹Hermoine says the description of a todo cannot be empty.");
            break;
        case EMPTY_EVENT:
            System.out.println("The Head Boy says the description of a event cannot be empty.");
            break;
        case EMPTY_DEADLINE:
            System.out.println("The Head Girl says the description of a deadline cannot be empty.");
            break;
        case NO_DDL_KEYWORD:
            System.out.println("Missing important keyword, add a /by before time.");
            break;
        case NO_EVENT_KEYWORD:
            System.out.println("Missing important keyword, add a /at before time.");
            break;
        case INVALID_ID:
            System.out.println("Not a number.");
            break;
        case OUT_OF_RANGE:
            System.out.println("Can't find the item in your list.");
            System.out.println("Give me a number from 1 to " + taskCount + ".");
            break;
        default:
            System.out.println("UNIDENTIFIED ERROR!");
        }
    }
}
