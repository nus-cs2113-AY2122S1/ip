package duke;

import java.util.Scanner;

public class Parser {

    private static final String BYE = "bye";
    private static final String HELLO= "hello";
    private static final String HI = "hi";
    private static final String YO = "yo";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BY= "/by";
    private static final String AT = "/at";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String HELP = "help";
    private static final String FIND = "find";

    public static void parse(Scanner input)  {
//        Scanner input = new Scanner(System.in);

        String taskType;
        String taskName;
        String taskDetails;
        boolean isExit = false;

        do {
            taskType = parseFirstWord(input.next());
            String remainingWords = input.nextLine();
            taskName = parseTaskName(remainingWords);
            taskDetails = parseTaskDetails(remainingWords);

            switch (taskType) {
            case BYE:
                isExit = parseBye();
                break;
            case HELLO:
            case HI:
            case YO:
                parseGreetings();
                break;
            case LIST:
                parseList();
                break;
            case TODO:
                parseTodo(taskName);
                break;
            case DEADLINE:
                parseDeadline(taskName, taskDetails);
                break;
            case EVENT:
                parseEvent(taskName, taskDetails);
                break;
            case DONE:
                parseDone(taskName);
                break;
            case DELETE:
                parseDelete(taskName);
                break;
            case FIND:
                parseFind(taskName);
                break;
            case HELP:
                parseHelp(taskName);
                break;
            default:
                parseDefault(taskType, remainingWords);
                break;
            }
        } while (!isExit);
    }

    private static String parseFirstWord(String text) {
        String[] words = text.split(" ");
        return words[0].toLowerCase();
    }

    protected static String parseTaskName(String text) {
        if (!text.isEmpty()) {
            text = text.substring(1);
        }

        if (text.contains(BY)) {
            String[] words = text.split(BY);
            text = words[0];
        } else if (text.contains(AT)) {
            String[] words = text.split(AT);
            text = words[0];
        }
        return text;
    }

    private static String parseTaskDetails(String text) {
        if (!text.isEmpty()) {
            text = text.substring(1);
        }

        if (text.contains(BY)) {
            String[] words = text.split(BY);
            text = words[1];
        } else if (text.contains(AT)) {
            String[] words = text.split(AT);
            text = words[1];
        }
        return text;
    }

    protected static boolean parseBye() {
        Storage.saveTaskToFile(TaskList.tasks);
        return true;
    }

//todo should I use parse methods, since they are meaningless one-liners?
    private static void parseGreetings() {
        Ui.mockUser();
    }

    private static void parseList() {
        Ui.showList(TaskList.tasks);
    }

    protected static void parseTodo(String taskName) {
        TaskList.addTask("T", taskName, "");
    }

    private static void parseDeadline(String taskName, String taskDetails) {
        TaskList.addTask("D", taskName, taskDetails);
    }

    private static void parseEvent(String taskName, String taskDetails) {
        TaskList.addTask("E", taskName, taskDetails);
    }

    private static void parseDone(String integer) {
        TaskList.doneTask(integer);
    }

    private static void parseDelete(String integer) {
        TaskList.deleteTask(integer);
    }

    private static void parseFind(String userInput) {
        try {
            TaskList.findTask(userInput);
        } catch (DukeException E) {
            Ui.showMissingTextError();
        }
    }

    private static void parseHelp(String message) {
        Ui.showHelpMessage(message);
    }

    protected static void parseDefault(String firstWord, String remainingWords) {
        Ui.showWrongTaskTypeError(firstWord + remainingWords);
    }
}
