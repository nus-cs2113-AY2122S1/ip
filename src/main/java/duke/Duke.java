package duke;

import duke.command.CommandList;
import duke.task.Task;
import java.util.Scanner;

public class Duke {
    private static final int ARRAY_SIZE = 100;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int ERROR_TODO_IS_EMPTY = 1;
    private static final int ERROR_EVENT_IS_EMPTY = 2;
    private static final int ERROR_DEADLINE_IS_EMPTY = 3;
    private static final int ERROR_COMMAND_NOT_FOUND = 4;
    private static final int ERROR_IS_INVALID = 5;
    private static final int CMD_NOT_FOUND = 0;
    private static final int CMD_TODO = 1;
    private static final int CMD_EVENT = 2;
    private static final int CMD_DEADLINE = 3;
    private static final int CMD_LIST = 4;
    private static final int CMD_DONE = 5;
    private static final int CMD_TERMINATE = 6;
    private static final Task[] items = new Task[ARRAY_SIZE];
    private static final String logo =
              " _____  ___  _____     ______\n"
            + "|___  | | | |_____|  / / -- \\ \\ \n"
            + "   / /  | |    / /  | |      | | \n"
            + "  / /   | |   / /   | |      | |\n"
            + " / /___ | |  /_/__  | |  --  | |\n"
            + "|_____| | | |_____|  \\ \\____/ /\n";
    private static final String border = "____________________________________________________________\n";


    public static void printStartMessage() {
        System.out.println(logo);
        System.out.println(border + "Hi bro, my name is Echo");
        System.out.println("What do you want?\n" + border);
        System.out.println("Type bye to exit\n" + border);
    }
    public static boolean isInvalid(CommandList task, String line) {
        if (!line.split("done")[1].trim().isEmpty()) {
            if (Integer.parseInt(line.split("done")[1].trim()) > task.getTaskCount()) {
                return true;
            }
        }
        return (line.length() <= FIVE);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        printStartMessage();
        CommandList task = new CommandList();
        DukeException error = new DukeException();
        do {
            line = in.nextLine();
            if (line.matches("list")) {
                task.setCommand(CMD_LIST);
                task.executeCommand(items, error, line);
                System.out.println(border);
            } else if (line.length() > FOUR && line.substring(0, FOUR).contains("done")) {
                if (isInvalid(task, line)) {
                    task.setCommand(CMD_NOT_FOUND);
                    error.setErrorType(ERROR_IS_INVALID);
                } else {
                    task.setCommand(CMD_DONE);
                }
                    task.executeCommand(items, error, line);
            } else if (line.length() >= FOUR && line.substring(0, FOUR).contains("todo")) { //improve condition to first word
                if (line.length() <= FIVE) {
                    error.setErrorType(ERROR_TODO_IS_EMPTY);
                    task.setCommand(CMD_NOT_FOUND);
                } else {
                    task.setCommand(CMD_TODO);
                }
                task.executeCommand(items, error, line);
            } else if (line.length() >= FIVE && line.substring(0, FIVE).contains("event")) {
                if (line.length() > SIX && line.contains("/at")) {
                    task.setCommand(CMD_EVENT);
                } else {
                    error.setErrorType(ERROR_EVENT_IS_EMPTY);
                    task.setCommand(CMD_NOT_FOUND);
                }
                task.executeCommand(items, error, line);
            } else if (line.length() >= EIGHT && line.substring(0, EIGHT).contains("deadline")) {
                if (line.length() >= NINE && line.contains("/by")) {
                    task.setCommand(CMD_DEADLINE);
                } else {
                    error.setErrorType(ERROR_DEADLINE_IS_EMPTY);
                    task.setCommand(CMD_NOT_FOUND);
                }
                task.executeCommand(items, error, line);
            } else if (!line.matches("bye")) {
                error.setErrorType(ERROR_COMMAND_NOT_FOUND);
                task.setCommand(CMD_NOT_FOUND);
                task.executeCommand(items, error, line);
            }
        } while (!line.matches("bye"));
        task.setCommand(CMD_TERMINATE);
        task.executeCommand(items, error, line);
    }
}
