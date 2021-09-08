import java.util.Scanner;

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
    public static final String INPUT_MORE_THAN_NUMBER_OF_TASKS = "Woah, you don't have THAT many things to do!";
    public static final String LIST_IS_EMPTY = "You're a free man :)";
    public static final String MISSING_ARGUMENTS_FOR_EVENT_AND_DEADLINE = "Sorry," +
            " you're missing some arguments, do type 'help' if you're unsure :)";
    public static final String PROMPT_TASK_NUMBER = "Please tell me which task you finished :)";
    public static final String PROMPT_SENSIBLE_INDEX = "Please give a number between 1-100 :)";
    public static Task[] tasks = new Task[100];

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

            } catch (NullPointerException error) {
                printErrorMessage(INPUT_MORE_THAN_NUMBER_OF_TASKS);

            } catch (DukeException error) {
                printErrorMessage(PROMPT_TASK_NUMBER);

            } catch (ArrayIndexOutOfBoundsException error) {
                printErrorMessage(PROMPT_SENSIBLE_INDEX);

            } catch (NumberFormatException error) {
                printErrorMessage(PROMPT_SENSIBLE_INDEX);

            }
            break;
        case "todo":
            try {
                executeTaskCase(input, TODO_STARTING_INDEX,TaskType.TODO);

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
        default:
            printErrorMessage(TYPE_SUITABLE_COMMAND_MESSAGE);
        }
    }

    private static void printTaskMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(ADDED_TO_LIST);
        printWordsWithIndentation(tasks[Task.getTotalTasks() - 1].getStatusIconAndDescription());
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
        }
        return parsedOutput;
    }

    private static void addTask(String input,TaskType type) throws StringIndexOutOfBoundsException, DukeException {
        String[] parsedOutput = parseInputForDifferentTask(input,type);
        switch(type){
        case TODO:
            tasks[Task.getTotalTasks()] = new ToDo(parsedOutput[0]);
            break;
        case EVENT:
            if(parsedOutput.length < 2) {
                throw new DukeException();
            }
            tasks[Task.getTotalTasks()] = new Event(parsedOutput[0], parsedOutput[1]);
            break;
        case DEADLINE:
            if(parsedOutput.length < 2) {
                throw new DukeException();
            }
            tasks[Task.getTotalTasks()] = new Deadline(parsedOutput[0], parsedOutput[1]);
            break;
        }
        Task.setTotalTasks(Task.getTotalTasks() + 1);
    }

    private static String notifyNumberOfTasks() {
        return "Now you have " + Task.getTotalTasks() + " task(s) in the list";
    }

    private static void executeDoneCase(String input) throws NullPointerException,DukeException,
            ArrayIndexOutOfBoundsException, NumberFormatException{
        if(input.length() < 6) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[index].markAsDone();
        printTaskCompletedMessage(tasks[index]);
    }

    private static void printTaskCompletedMessage(Task task) {
        printIndentationAndDivider();
        printWordsWithIndentation(TASK_COMPLETED_MESSAGE);
        printWordsWithIndentation(task.getStatusIconAndDescription());
        printIndentationAndDivider();
        System.out.println();
    }

    private static void executeListCase() {
        printIndentationAndDivider();
        if(Task.getTotalTasks() == 0) {
            printWordsWithIndentation(LIST_IS_EMPTY);
        }
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            printWordsWithIndentation(i + 1 + "." + tasks[i].getStatusIconAndDescription());
        }
        printIndentationAndDivider();
        System.out.println();
    }
}
