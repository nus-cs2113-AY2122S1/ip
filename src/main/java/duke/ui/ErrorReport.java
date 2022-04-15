package duke.ui;

import duke.util.Alarm;

public class ErrorReport {

    public static void outOfRangeException(int taskCount) {
        System.out.println("Can't find the item in your list.");
        System.out.println("Give me a number from 1 to " + taskCount + ".");
    }

    public static void alarm(Alarm typeOfAlarm) {
        switch(typeOfAlarm) {
        case INCOMPLETE_TASK:
            System.out.println("Professor McGonagall says your task description is incomplete");
            break;
        case EMPTY_FIND:
            System.out.println("Professor Dumbledore says be clear what you are looking for.");
            break;
        case INVALID_COMMAND:
            System.out.println("Invalid charm. What did Professor Flitwick told you?");
            //System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        case BLANK_DESCRIPTION:
            System.out.println("Hermoine says the description of a task cannot be empty.");
            break;
        case EMPTY_INDEX:
            System.out.println("Give me a number.");
            break;
        case EMPTY_TODO:
            System.out.println("Hermoine says the description of a todo cannot be empty.");
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
        case EMPTY_LIST:
            System.out.println("No items in list yet.");
            break;
        case SAVE_ERROR:
            System.out.print("Something went wrong, data can't be saved.");
        default:
            System.out.println("UNIDENTIFIED ERROR!");
        }
    }
}
