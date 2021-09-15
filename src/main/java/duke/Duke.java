package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskType;
import task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String DIVIDER = "___________________________________________________________";
    public static final String INDENTATION = "      ";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String HELLO_MESSAGE_2 = "Hello! I'm Duke, your friendly neighbourhood task manager";
    public static final String HELLO_MESSAGE_3 = "What can I do for you? :D";
    public static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";
    public static final String ADDED_TO_LIST = "I've added this to your list :D";
    public static final String DEADLINE_DESCRIPTION_AND_DATE_SPLITTER = " /by ";
    public static final String EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER = " /at ";
    public static final String GOODBYE_MESSAGE = "Bye, hope to see you again soon! :)";
    public static final String TYPE_SUITABLE_COMMAND_MESSAGE = "Sorry, I don't know what you mean. " +
            "Please give me a suitable command :)";
    public static final int TODO_STARTING_INDEX = 5;
    public static final int DEADLINE_STARTING_INDEX = 9;
    public static final int EVENT_STARTING_INDEX = 6;
    public static final String PROMPT_TASK_DESCRIPTION = "Please tell me what do you need to do :)";
    public static final String LIST_IS_EMPTY = "You're a free man :)";
    public static final String MISSING_ARGUMENTS_FOR_EVENT_AND_DEADLINE = "Sorry," +
            " you're missing some arguments, do type 'help' if you're unsure :)";
    public static final String PROMPT_TASK_NUMBER = "Please tell me which task you want to select :)";
    public static final String PROMPT_SENSIBLE_INDEX = "Please give a number between 1 and ";
    public static final int EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE = 2;
    public static final int EXPECTED_LENGTH_FOR_DONE_INPUT = 6;
    public static final String DEFAULT_ERROR_MESSAGE = "Oops, something went wrong!";
    public static final int EXPECTED_LENGTH_FOR_DELETE_INPUT = 8;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printStartingMessage();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            processInput(input);
            input = in.nextLine();
        }
        printGoodbyeMessage();
    }

    public static void printIndentationAndDivider() {
        System.out.print(INDENTATION);
        System.out.println(DIVIDER);
    }

    public static void printWordsWithIndentation(String words) {
        System.out.print(INDENTATION);
        System.out.println(words);
    }

    private static void printStartingMessage() {
        System.out.println("Hello from\n" + LOGO);
        printIndentationAndDivider();
        printWordsWithIndentation(HELLO_MESSAGE_2);
        printWordsWithIndentation(HELLO_MESSAGE_3);
        printIndentationAndDivider();
        System.out.println();
    }

    private static void printGoodbyeMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(GOODBYE_MESSAGE);
        printIndentationAndDivider();
    }

    private static void processInput(String input) {
        switch(input.split(" ")[0].toLowerCase()) {
        case "list":
            executeListCase();
            break;
        case "done":
            try {
                executeDoneCase(input);

            } catch (DukeException error) {
                printErrorMessage(PROMPT_TASK_NUMBER);

            } catch (IndexOutOfBoundsException error) {
                printErrorMessage(getSensibleRange(Task.getTotalTasks()));

            } catch (NumberFormatException error) {
                printErrorMessage(getSensibleRange(Task.getTotalTasks()));

            }
            break;
        case "todo":
            try {
                executeTaskCase(input, TODO_STARTING_INDEX, TaskType.TODO);

            } catch (StringIndexOutOfBoundsException error) {
                printErrorMessage(PROMPT_TASK_DESCRIPTION);

            }
            break;
        case "deadline":
            try {
                executeTaskCase(input, DEADLINE_STARTING_INDEX,TaskType.DEADLINE);

            } catch (StringIndexOutOfBoundsException error) {
                printErrorMessage(PROMPT_TASK_DESCRIPTION);

            }
            break;
        case "event":
            try {
                executeTaskCase(input, EVENT_STARTING_INDEX,TaskType.EVENT);

            } catch (StringIndexOutOfBoundsException error) {
                printErrorMessage(PROMPT_TASK_DESCRIPTION);

            }
            break;
        case "delete":
            try {
                executeDeleteCase(input);

            } catch (DukeException error) {
                printErrorMessage(PROMPT_TASK_NUMBER);

            } catch (IndexOutOfBoundsException error) {
                printErrorMessage(getSensibleRange(Task.getTotalTasks()));

            } catch (NumberFormatException error) {
                printErrorMessage(getSensibleRange(Task.getTotalTasks()));

            }
            break;
        default:
            printErrorMessage(TYPE_SUITABLE_COMMAND_MESSAGE);

        }
    }

    private static void printTaskMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(ADDED_TO_LIST);
        printWordsWithIndentation(tasks.get(Task.getTotalTasks() - 1).getStatusIconAndDescription());
        printWordsWithIndentation(notifyNumberOfTasks());
        printIndentationAndDivider();
        System.out.println();
    }

    private static void printErrorMessage(String error_message) {
        printIndentationAndDivider();
        printWordsWithIndentation(error_message);
        printIndentationAndDivider();
        System.out.println();
    }

    private static void executeTaskCase(String input, int starting_index, TaskType type) {
        String description = input.strip().substring(starting_index);
        try {
            addTask(description, type);
            printTaskMessage();

        } catch (DukeException error) {
            printErrorMessage(MISSING_ARGUMENTS_FOR_EVENT_AND_DEADLINE);

        }
    }

    private static String[] parseInputForDifferentTask(String input,TaskType type) {
        String[] parsedOutput = {};
        switch(type) {
        case TODO:
            parsedOutput = new String[]{input};
            break;
        case DEADLINE:
            parsedOutput = input.split(DEADLINE_DESCRIPTION_AND_DATE_SPLITTER);
            break;
        case EVENT:
            parsedOutput = input.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER);
            break;
        default :
            printErrorMessage(DEFAULT_ERROR_MESSAGE);
            break;
        }
        return parsedOutput;
    }

    private static void addTask(String input,TaskType type)
            throws StringIndexOutOfBoundsException, DukeException {
        String[] parsedOutput = parseInputForDifferentTask(input,type);
        switch(type){
        case TODO:
            tasks.add(new ToDo(parsedOutput[0]));
            break;
        case EVENT:
            if(parsedOutput.length < EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE) {
                throw new DukeException();
            }
            tasks.add(new Event(parsedOutput[0], parsedOutput[1]));
            break;
        case DEADLINE:
            if(parsedOutput.length < EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE) {
                throw new DukeException();
            }
            tasks.add(new Deadline(parsedOutput[0], parsedOutput[1]));
            break;
        default :
            printErrorMessage(DEFAULT_ERROR_MESSAGE);
            return;
        }
        Task.setTotalTasks(Task.getTotalTasks() + 1);
    }

    private static String notifyNumberOfTasks() {
        return "Now you have " + Task.getTotalTasks() + " task(s) in the list";
    }

    private static void executeDoneCase(String input) throws DukeException,
            IndexOutOfBoundsException,NumberFormatException {
        if(input.length() < EXPECTED_LENGTH_FOR_DONE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(index).markAsDone();
        printTaskCompletedMessage(tasks.get(index));
    }

    private static void executeDeleteCase(String input) throws DukeException,
            IndexOutOfBoundsException,NumberFormatException {
        if(input.length() < EXPECTED_LENGTH_FOR_DELETE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        printTaskDeletedMessage(tasks.get(index));
        tasks.remove(index);
    }

    private static void printTaskCompletedMessage(Task task) {
        printIndentationAndDivider();
        printWordsWithIndentation(TASK_COMPLETED_MESSAGE);
        printWordsWithIndentation(task.getStatusIconAndDescription());
        printIndentationAndDivider();
        System.out.println();
    }

    private static void printTaskDeletedMessage(Task task) {
        Task.setTotalTasks(Task.getTotalTasks() - 1);
        printIndentationAndDivider();
        printWordsWithIndentation("Alright, I've removed this task:");
        printWordsWithIndentation(task.getStatusIconAndDescription());
        printWordsWithIndentation(notifyNumberOfTasks());
        printIndentationAndDivider();
        System.out.println();
    }

    private static void executeListCase() {
        printIndentationAndDivider();
        if(Task.getTotalTasks() == 0) {
            printWordsWithIndentation(LIST_IS_EMPTY);
        }
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            printTask(i);
        }
        printIndentationAndDivider();
        System.out.println();
    }

    /**
     *Prints out each task in a specific format
     */
    private static void printTask(int i) {
        printWordsWithIndentation(i + 1 + "." + tasks.get(i).getStatusIconAndDescription());
    }

    private static String getSensibleRange(int number) {
        if(number < 1) {
            return LIST_IS_EMPTY;
        }
        return PROMPT_SENSIBLE_INDEX + number + " :)";
    }
}
