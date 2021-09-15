package task;

import error.exception.DukeEmptyTaskDescriptionException;
import error.exception.DukeInvalidDescriptionFormatException;
import error.Error;
import error.Printer;
import task.subtask.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static int count = 0;
    public static ArrayList<Task> list = new ArrayList<>();
    static Storage storage = new Storage();

    /**
     * Prints the logo of the chatbot and calls userCommands for reading user input.
     * Data is also loaded at this stage.
     */
    public static void main(String[] args) {
        //logo
        String logo = " _____  ________    _        ______  ___  ___  ______\n"
                + "|_   _||  _   _ |  / \\    .' ___  | |_  ||  _||__   _|\n"
                + "  | |     | |     / _ \\  / .'   \\_   | |__| |    | |\n"
                + "  | |     | |    / ___ \\ | |         |  __  |    | |\n"
                + " _| |_   _| |_ _/ /     \\ \\ `.___.'\\_| |  | |   _| |_\n"
                + "|_____| |_____|____| |____ `.____ .|____||____||_____|\n";

        System.out.println("THE GREATEST SHINOBI\n" + logo
                + "\nWelcome! You have entered my illusion where I will be your partner\n"
                + "and I will make you productive in order for you to reach your goals\n"
                + "so that I can fulfill my dream of making someone great.\n"
                + "Go ahead, give your command\n");

        Printer.printWelcomeMessage();
        storage.loadData();
        userCommands();
    }

    /**
     * Reads the different types of user input/commands until bye command is received.
     */
    private static void userCommands() {
        boolean isOver = false;
        Scanner in = new Scanner(System.in);

        while (!isOver) {
            String input = TaskManager.getUserInput(in);
            String command = TaskManager.getFirstWordFromCommand(input);

            switch (command) {
            case "bye":
                Printer.printByeMessage();
                isOver = true;
                break;
            case "list":
                Printer.printLineSeparator();
                System.out.println("Here are the tasks in your list:");

                //Lists down all the tasks added along with its status
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }

                Printer.printLineSeparator();
                break;
            case "delete":
                try {
                    int indexOfDelete = TaskManager.getIndex(input);

                    Printer.printLineSeparator();
                    System.out.println("Noted. I've deleted this task:\n" + list.get(indexOfDelete) + "\nNow you have " + (count - 1) + " tasks in the list.");
                    Printer.printLineSeparator();

                    list.remove(indexOfDelete);
                    count--;
                    storage.saveData(list);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    Error.showDeleteFormatError();
                }
                break;
            case "done":
                try {
                    //Extracts the index number from the text and changes status of the task
                    int indexOfDone = TaskManager.getIndex(input);
                    list.get(indexOfDone).markAsDone();

                    Printer.printLineSeparator();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(indexOfDone));
                    Printer.printLineSeparator();
                    storage.saveData(list);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    Error.showDoneFormatError();
                }
                break;
            case "todo":
                try {
                    Task todo = TaskManager.getTodoDetails(input);

                    Printer.printLineSeparator();
                    System.out.println("Got it. I've added this task:\n" + todo + "\nNow you have " + count + " tasks in the list.");
                    Printer.printLineSeparator();
                    storage.saveData(list);
                } catch (DukeEmptyTaskDescriptionException e) {
                    Error.showTaskDescriptionError();
                }
                break;
            case "deadline":
                try {
                    Task deadline = TaskManager.getDeadlineDetails(input);

                    Printer.printLineSeparator();
                    System.out.println("Got it. I've added this task:\n" + deadline + "\nNow you have " + count + " tasks in the list.");
                    Printer.printLineSeparator();
                    storage.saveData(list);
                } catch (DukeInvalidDescriptionFormatException e) {
                    Error.showDeadlineFormatError();
                } catch (DukeEmptyTaskDescriptionException e) {
                    Error.showTaskDescriptionError();
                }
                break;
            case "event":
                try {
                    Task event = TaskManager.getEventDetails(input);

                    Printer.printLineSeparator();
                    System.out.println("Got it. I've added this task:\n" + event + "\nNow you have " + count + " tasks in the list.");
                    Printer.printLineSeparator();
                    storage.saveData(list);
                } catch (DukeInvalidDescriptionFormatException e) {
                    Error.showEventFormatError();
                } catch (DukeEmptyTaskDescriptionException e) {
                    Error.showTaskDescriptionError();
                }
                break;
            default:
                Error.showInvalidCommandError();
            }
        }
    }
}