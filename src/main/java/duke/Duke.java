package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {
    public static final String TEXT_SPACER = "    ";

    public static void main(String[] args) {
        printWelcome();
        printLineSpacer();
        System.out.println(TEXT_SPACER + "Hey, what's up!\n" + TEXT_SPACER + "What can I help you with today?");
        printLineSpacer();

        Task[] userLists = new Task[100];
        Scanner scanner = new Scanner(System.in);
        String userLineInput;
        int numOfTasks = 0;

        do {
            userLineInput = scanner.nextLine();
            String[] splitInputs = userLineInput.split(" ");
            String userCommand = splitInputs[0];
            printLineSpacer();
            if (!userCommand.equals(("bye"))) {
                try {
                    switch (userCommand) {
                    case "list":
                        printList(userLists, numOfTasks);
                        break;
                    case "done":
                        try {
                            completeTask(userLists, splitInputs);
                        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                            System.out.println("Please choose a valid list entry.");
                        }
                        break;
                    case "event":
                    case "deadline":
                    case "todo":
                        try {
                            storeTasks(userLists, userLineInput, numOfTasks, userCommand);
                            numOfTasks++;
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(TEXT_SPACER +
                                    "Description of your task can't be empty. At least enter something!");
                        }
                        break;
                    default:
                        throw new DukeExceptions(TEXT_SPACER +
                                "Sorry mate, I don't understand what you are trying to get me to do.");
                    }
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                }
                printLineSpacer();
            }
        } while(!userLineInput.equals("bye"));

        System.out.println(TEXT_SPACER + "Aight. See you soon mate.");
        printLineSpacer();
    }

    private static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printList(Task[] userLists, int numOfTasks) {
        if (numOfTasks == 0) {
            System.out.println(TEXT_SPACER + "List is empty!");
        } else {
            System.out.println(TEXT_SPACER + "Here's your list of tasks:");
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println("    " + (i + 1) + "." + userLists[i].toString());
            }
        }
    }

    private static void completeTask(Task[] userLists, String[] splitInput) {
        int chosenEntry;
        chosenEntry = Integer.parseInt(splitInput[1]) - 1;
        userLists[chosenEntry].markAsDone();
        System.out.println(TEXT_SPACER + "Good job on completing a task!");
        System.out.println(TEXT_SPACER + userLists[chosenEntry].toString());
    }

    private static void storeTasks(Task[] userLists, String userLineInput,
                                   int numOfTasks, String userCommand) throws DukeExceptions{
        if (userCommand.equals("event")){
            storeEvent(userLists, userLineInput, numOfTasks);
        } else if(userCommand.equals("deadline")) {
            storeDeadline(userLists, userLineInput, numOfTasks);
        } else {
            storeToDo(userLists, userLineInput, numOfTasks);
        }
        System.out.println(TEXT_SPACER + "Now you have " + (numOfTasks + 1) + " tasks in your list");
    }

    private static void storeToDo(Task[] userLists, String userLineInput, int numOfTasks) {
        userLists[numOfTasks] = new ToDo(userLineInput.substring(5));
        System.out.println(TEXT_SPACER + "Aight, I've added the following task to your list:");
        System.out.println(TEXT_SPACER + userLists[numOfTasks].toString());
    }

    private static void storeDeadline(Task[] userLists, String userLineInput, int numOfTasks) throws DukeExceptions{
        int infoIndex = userLineInput.indexOf("/");
        //checks for "/by" in the user input
        if ((infoIndex <= 9) && userLineInput.length() > 8) {
            throw new DukeExceptions(TEXT_SPACER + "Hey, please use proper formatting." +
                    System.lineSeparator() + TEXT_SPACER + "You should add a /by before your due date.");
        }
        String taskDescription = userLineInput.substring(9, infoIndex);
        String dueDate = userLineInput.substring(infoIndex + 3);
        userLists[numOfTasks] = new Deadline(taskDescription, dueDate);
        System.out.println(TEXT_SPACER + userLists[numOfTasks].toString());
    }

    private static void storeEvent(Task[] userLists, String userLineInput, int numOfTasks) throws DukeExceptions{
        int infoIndex = userLineInput.indexOf("/");
        //checks for "/at" in the user input
        if ((infoIndex <= 6) && userLineInput.length() > 5) {
            throw new DukeExceptions(TEXT_SPACER + "Hey, please use proper formatting." +
                    System.lineSeparator() + TEXT_SPACER + "You should add a /at before your event date");
        }
        String taskDescription = userLineInput.substring(6, infoIndex);
        String eventTime = userLineInput.substring(infoIndex + 3);
        userLists[numOfTasks] = new Event(taskDescription, eventTime);
        System.out.println(TEXT_SPACER + userLists[numOfTasks].toString());
    }


    private static void printLineSpacer() {
        System.out.println(TEXT_SPACER + "**************************************************");
    }
}
